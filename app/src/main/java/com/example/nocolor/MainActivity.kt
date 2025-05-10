package com.example.nocolor

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout.LayoutParams
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import android.Manifest
import androidx.core.graphics.drawable.DrawableCompat


class MainActivity : AppCompatActivity() {

    private lateinit var pixelArt: Array<IntArray>
    private lateinit var colors: List<Int>
    private var selectedColorIndex = 0
    private lateinit var pixelGrid: GridView
    private lateinit var pixelAdapter: PixelAdapter
    private lateinit var userPixels: Array<IntArray>

    // Zoom
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    private lateinit var gridContainer: View

    // Drag mode
    private var isDragMode = false
    private var dragPixelValue = -1
    private lateinit var gestureDetector: GestureDetector

    // Magic mode
    private var isMagicMode = false

    // Drawing mode
    private var isDrawingMode = false

    // Progress
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    // SharedPreferences để lưu tiến trình
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "PixelArtPrefs"
    private val KEY_USER_PIXELS = "user_pixels"
    private val KEY_BACKGROUND_STYLE = "background_style"

    // Kiểu background
    private var selectedBackgroundStyle = BackgroundStyle.PLAIN
    private enum class BackgroundStyle {
        PLAIN, LEGO, CROSS
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridContainer = findViewById(R.id.gridContainer)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadBackgroundStyle()

        // Khởi tạo ProgressBar và TextView
        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)

        // Khởi tạo ScaleGestureDetector
        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            private var focusX = 0f
            private var focusY = 0f

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                focusX = detector.focusX
                focusY = detector.focusY
                return true
            }

            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val newScaleFactor = scaleFactor * detector.scaleFactor
                val limitedScaleFactor = newScaleFactor.coerceIn(0.5f, 3.0f)

                if (abs(limitedScaleFactor - scaleFactor) > 0.01f) {
                    scaleFactor = limitedScaleFactor
                    gridContainer.scaleX = scaleFactor
                    gridContainer.scaleY = scaleFactor
                    gridContainer.pivotX = focusX - gridContainer.left
                    gridContainer.pivotY = focusY - gridContainer.top
                }
                return true
            }
        })

        gridContainer.setOnTouchListener { view, event ->
            scaleGestureDetector.onTouchEvent(event)
            if (event.action == MotionEvent.ACTION_UP) {
                view.performClick()
            }
            true
        }

        // Khởi tạo GestureDetector
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                val rect = Rect()
                pixelGrid.getGlobalVisibleRect(rect)
                val x = e.rawX - rect.left
                val y = e.rawY - rect.top
                val position = pixelGrid.pointToPosition(x.toInt(), y.toInt())

                Log.d("MainActivity", "Double tap at x=${e.rawX}, y=${e.rawY}, adjusted x=$x, y=$y, position=$position")

                if (position != GridView.INVALID_POSITION) {
                    val row = position / pixelArt[0].size
                    val col = position % pixelArt[0].size
                    val pixelValue = pixelArt[row][col]

                    if (pixelValue != 0 && userPixels[row][col] == 0) {
                        isDragMode = true
                        dragPixelValue = pixelValue
                        if (pixelValue == selectedColorIndex + 1) {
                            userPixels[row][col] = pixelValue
                            pixelAdapter.notifyDataSetChanged()
                            updateProgress()
                            saveUserPixels()
                            Log.d("MainActivity", "Started drag mode at ($row, $col) with pixelValue=$pixelValue")
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Bạn phải chọn màu số $pixelValue để tô ô này",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                return true
            }
        })

        // Lấy dữ liệu PixelArt từ Intent
        val pixelArtParcelable = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PIXEL_ART", PixelArtParcelable::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<PixelArtParcelable>("PIXEL_ART")
        }
        val pixelArtData = pixelArtParcelable?.toPixelArt()
        pixelArt = pixelArtData?.pixelData ?: arrayOf(
            intArrayOf(0, 0, 1, 1, 1, 0, 0),
            intArrayOf(0, 1, 2, 2, 2, 1, 0),
            intArrayOf(1, 2, 3, 3, 3, 2, 1),
            intArrayOf(1, 2, 3, 4, 3, 2, 1),
            intArrayOf(1, 2, 3, 3, 3, 2, 1),
            intArrayOf(0, 1, 2, 2, 2, 1, 0),
            intArrayOf(0, 0, 1, 1, 1, 0, 0)
        )
        colors = pixelArtData?.colors ?: listOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW
        )

        // Cập nhật tiêu đề
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = pixelArtParcelable?.title ?: "Tô Màu Pixel"

        // Khởi tạo mảng lưu trạng thái tô màu của người dùng
        userPixels = Array(pixelArt.size) { IntArray(pixelArt[0].size) { 0 } }
        loadUserPixels()

        // Thiết lập bảng màu
        setupColorPalette()

        // Thiết lập grid pixel
        pixelGrid = findViewById(R.id.pixelGrid)
        pixelAdapter = PixelAdapter()
        pixelGrid.adapter = pixelAdapter
        pixelGrid.numColumns = pixelArt[0].size

        pixelGrid.post {
            val columnWidth = pixelGrid.width / pixelArt[0].size
            val layoutParams = pixelGrid.layoutParams
            layoutParams.height = columnWidth * pixelArt.size
            pixelGrid.layoutParams = layoutParams

            pixelAdapter.cellSize = columnWidth
            pixelAdapter.notifyDataSetChanged()
        }

        // Thiết lập OnTouchListener cho GridView
        pixelGrid.setOnTouchListener { view, event ->
            val handled = gestureDetector.onTouchEvent(event)
            if (handled) {
                Log.d("MainActivity", "GestureDetector handled event: ${event.action}")
            }

            if (isDragMode) {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val rect = Rect()
                        pixelGrid.getGlobalVisibleRect(rect)
                        val x = event.rawX - rect.left
                        val y = event.rawY - rect.top
                        val position = pixelGrid.pointToPosition(x.toInt(), y.toInt())

                        Log.d("MainActivity", "Drag MOVE: x=$x, y=$y, position=$position")

                        if (position != GridView.INVALID_POSITION) {
                            val row = position / pixelArt[0].size
                            val col = position % pixelArt[0].size
                            val pixelValue = pixelArt[row][col]

                            if (pixelValue == dragPixelValue && userPixels[row][col] == 0) {
                                if (pixelValue == selectedColorIndex + 1) {
                                    userPixels[row][col] = pixelValue
                                    pixelAdapter.notifyDataSetChanged()
                                    saveUserPixels()
                                    Log.d("MainActivity", "Painted pixel at ($row, $col) with value $pixelValue")
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Bạn phải chọn màu số $pixelValue để tô ô này",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        isDragMode = false
                        dragPixelValue = -1
                        Log.d("MainActivity", "Drag mode ended")
                        view.performClick()
                    }
                }
            }

            if (isDrawingMode) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        val rect = Rect()
                        pixelGrid.getGlobalVisibleRect(rect)
                        val x = event.rawX - rect.left
                        val y = event.rawY - rect.top
                        val position = pixelGrid.pointToPosition(x.toInt(), y.toInt())

                        Log.d("MainActivity", "Drawing: x=$x, y=$y, position=$position")

                        if (position != GridView.INVALID_POSITION) {
                            val row = position / pixelArt[0].size
                            val col = position % pixelArt[0].size
                            val pixelValue = pixelArt[row][col]

                            if (pixelValue != 0 && userPixels[row][col] == 0 && pixelValue == selectedColorIndex + 1) {
                                userPixels[row][col] = pixelValue
                                pixelAdapter.notifyDataSetChanged()
                                updateProgress()
                                saveUserPixels()
                                Log.d("MainActivity", "Drawing painted pixel at ($row, $col) with value $pixelValue")
                            }
                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        view.performClick()
                    }
                }
            }

            scaleGestureDetector.onTouchEvent(event)
            true
        }

        // Thiết lập magicButton
        findViewById<ImageButton>(R.id.magicButton)?.setOnClickListener {
            isMagicMode = !isMagicMode
            isDrawingMode = false // Tắt drawing mode khi bật/tắt magic mode
            findViewById<ImageButton>(R.id.drawingButton)?.isSelected = false
            Toast.makeText(
                this,
                if (isMagicMode) "Chế độ tô màu hàng loạt đã bật" else "Chế độ tô màu hàng loạt đã tắt",
                Toast.LENGTH_SHORT
            ).show()
        }

        // drawingButton vẽ kiểu re chuột
        findViewById<ImageButton>(R.id.drawingButton)?.setOnClickListener {
            isDrawingMode = !isDrawingMode
            isMagicMode = false // Tắt magic mode
            findViewById<ImageButton>(R.id.magicButton)?.isSelected = false
            it.isSelected = isDrawingMode
            Toast.makeText(
                this,
                if (isDrawingMode) "Chế độ vẽ rê chuột đã bật" else "Chế độ vẽ rê chuột đã tắt",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Nút kiểm tra hoàn thành
        findViewById<ImageButton>(R.id.checkButton)?.setOnClickListener {
            if (isArtCompleted()) {
                Toast.makeText(this, "Chúc mừng! Bạn đã hoàn thành bức tranh!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Bạn chưa hoàn thành bức tranh!", Toast.LENGTH_SHORT).show()
            }
            updateProgress()
        }

        //  clearButton
        findViewById<ImageButton>(R.id.clearButton)?.setOnClickListener {
            clearAllPixels()
            Toast.makeText(this, "Đã xóa toàn bộ màu đã tô!", Toast.LENGTH_SHORT).show()
        }

        //  changeBackgroundButton
        findViewById<ImageButton>(R.id.changeBackgroundButton)?.setOnClickListener {
            showBackgroundSelectionDialog()
        }

        // resetButton
        findViewById<ImageButton>(R.id.resetButton)?.setOnClickListener {
            scaleFactor = 1.0f
            gridContainer.scaleX = scaleFactor
            gridContainer.scaleY = scaleFactor
            gridContainer.pivotX = gridContainer.width / 2f
            gridContainer.pivotY = gridContainer.height / 2f
            Toast.makeText(this, "Đã đặt lại kích thước ban đầu", Toast.LENGTH_SHORT).show()
        }

        // Thiết lập downButton
        findViewById<ImageButton>(R.id.downButton).setOnClickListener {
            if (checkArtCompletion()) {
                if (checkStoragePermission()) {
                    savePixelArtToGallery()
                }
            } else {
                Toast.makeText(
                    this,
                    "Vui lòng hoàn thành bức tranh trước khi tải xuống!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    // palet màu
    private fun setupColorPalette() {
        val paletteLayout = findViewById<LinearLayout>(R.id.colorPalette)
        paletteLayout.removeAllViews()

        val baseSize = resources.getDimensionPixelSize(R.dimen.color_button_size)
        val margin = resources.getDimensionPixelSize(R.dimen.color_button_margin)
        val selectedScale = 1.2f

        colors.forEachIndexed { index, color ->
            val number = index + 1
            val isSelected = index == selectedColorIndex
            val size = if (isSelected) (baseSize * selectedScale).toInt() else baseSize

            val colorView = FrameLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(size, size).apply {
                    setMargins(margin, 0, margin, 0)
                }

                val colorCircle = View(this@MainActivity).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        (size * 0.9f).toInt(),
                        (size * 0.9f).toInt()
                    ).apply {
                        gravity = Gravity.CENTER
                    }
                    background = GradientDrawable().apply {
                        shape = GradientDrawable.OVAL
                        setColor(color)
                        setStroke(
                            if (isSelected) 8 else 4,
                            if (isSelected) Color.GRAY else Color.BLACK
                        )
                    }
                }

                val textView = TextView(this@MainActivity).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        gravity = Gravity.CENTER
                    }
                    text = number.toString()
                    setTextColor(if (isColorDark(color)) Color.WHITE else Color.BLACK)
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD)
                }

                addView(colorCircle)
                addView(textView)

                setOnClickListener {
                    selectedColorIndex = index
                    setupColorPalette()
                }
            }

            paletteLayout.addView(colorView)
        }
    }

    private fun isColorDark(color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness > 0.5
    }


    //thanh tiến trình
    private fun updateProgress() {
        var totalPixelsToColor = 0
        var coloredPixels = 0

        for (i in pixelArt.indices) {
            for (j in pixelArt[i].indices) {
                if (pixelArt[i][j] != 0) {
                    totalPixelsToColor++
                    if (userPixels[i][j] != 0) {
                        coloredPixels++
                    }
                }
            }
        }

        val progress = if (totalPixelsToColor > 0) {
            (coloredPixels * 100) / totalPixelsToColor
        } else {
            0
        }

        progressBar.progress = progress
        progressText.text = "$progress%"
    }


    private fun fillAllPixelsWithValue(pixelValue: Int) {
        if (pixelValue == selectedColorIndex + 1) {
            for (i in pixelArt.indices) {
                for (j in pixelArt[i].indices) {
                    if (pixelArt[i][j] == pixelValue && userPixels[i][j] == 0) {
                        userPixels[i][j] = pixelValue
                    }
                }
            }
            pixelAdapter.notifyDataSetChanged()
            updateProgress()
            saveUserPixels()
            if (isArtCompleted()) {
                showCongratulationsDialog()
            }
        } else {
            Toast.makeText(
                this,
                "Bạn phải chọn màu số $pixelValue để tô các ô này",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveUserPixels() {
        val editor = sharedPreferences.edit()
        val pixelsString = userPixels.joinToString(",") { row ->
            row.joinToString(",")
        }
        editor.putString(KEY_USER_PIXELS, pixelsString)
        editor.apply()
    }

    private fun loadUserPixels() {
        val pixelsString = sharedPreferences.getString(KEY_USER_PIXELS, null)
        if (pixelsString != null) {
            val rows = pixelsString.split(",")
            if (rows.size == pixelArt.size * pixelArt[0].size) {
                for (i in userPixels.indices) {
                    for (j in userPixels[i].indices) {
                        val index = i * pixelArt[0].size + j
                        userPixels[i][j] = rows[index].toIntOrNull() ?: 0
                    }
                }
            }
        }
        updateProgress()
    }

    private fun clearAllPixels() {
        for (i in userPixels.indices) {
            for (j in userPixels[i].indices) {
                userPixels[i][j] = 0
            }
        }
        pixelAdapter.notifyDataSetChanged()
        updateProgress()
        saveUserPixels()
    }

    private fun showCongratulationsDialog() {
        val dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.dialog_congrats)

            findViewById<Button>(R.id.btnClose).setOnClickListener {
                dismiss()
            }

            window?.setLayout(
                (resources.displayMetrics.widthPixels * 0.85).toInt(),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }

        dialog.show()
    }

    private fun showBackgroundSelectionDialog() {
        val dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_background_selection)
            setCancelable(true)

            val radioGroup = findViewById<RadioGroup>(R.id.backgroundRadioGroup)
            val plainRadio = findViewById<RadioButton>(R.id.radioPlain)
            val legoRadio = findViewById<RadioButton>(R.id.radioLego)
            val crossRadio = findViewById<RadioButton>(R.id.radioCross)
            val confirmButton = findViewById<Button>(R.id.btnConfirm)
            val cancelButton = findViewById<Button>(R.id.btnCancel)

            when (selectedBackgroundStyle) {
                BackgroundStyle.PLAIN -> plainRadio.isChecked = true
                BackgroundStyle.LEGO -> legoRadio.isChecked = true
                BackgroundStyle.CROSS -> crossRadio.isChecked = true
            }

            confirmButton.setOnClickListener {
                val selectedId = radioGroup.checkedRadioButtonId
                selectedBackgroundStyle = when (selectedId) {
                    R.id.radioPlain -> BackgroundStyle.PLAIN
                    R.id.radioLego -> BackgroundStyle.LEGO
                    R.id.radioCross -> BackgroundStyle.CROSS
                    else -> BackgroundStyle.PLAIN
                }
                saveBackgroundStyle()
                pixelAdapter.notifyDataSetChanged()
                dismiss()
                Toast.makeText(this@MainActivity, "Đã thay đổi kiểu background", Toast.LENGTH_SHORT).show()
            }

            cancelButton.setOnClickListener {
                dismiss()
            }

            window?.setLayout(
                (resources.displayMetrics.widthPixels * 0.85).toInt(),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }

        dialog.show()
    }

    private fun saveBackgroundStyle() {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_BACKGROUND_STYLE, selectedBackgroundStyle.name)
        editor.apply()
    }

    private fun loadBackgroundStyle() {
        val styleName = sharedPreferences.getString(KEY_BACKGROUND_STYLE, BackgroundStyle.PLAIN.name)
        selectedBackgroundStyle = try {
            BackgroundStyle.valueOf(styleName ?: BackgroundStyle.PLAIN.name)
        } catch (e: IllegalArgumentException) {
            BackgroundStyle.PLAIN
        }
    }

    private fun isArtCompleted(): Boolean {
        for (i in pixelArt.indices) {
            for (j in pixelArt[i].indices) {
                if (pixelArt[i][j] != 0 && userPixels[i][j] == 0) {
                    return false
                }
            }
        }
        showCongratulationsDialog()
        return true
    }

    private fun numberToGrayscale(pixelValue: Int): Int {
        return when (pixelValue) {
            1 -> Color.rgb(120, 120, 120)
            2 -> Color.rgb(140, 140, 140)
            3 -> Color.rgb(180, 180, 180)
            4 -> Color.rgb(220, 220, 180)
            5 -> Color.rgb(190, 190, 190)
            6 -> Color.rgb(150, 150, 150)
            else -> Color.WHITE
        }
    }

    // Hàm lưu ảnh pixel art vào thư viện ảnh
    private fun savePixelArtToGallery() {
        // Đảm bảo thực hiện trên luồng UI
        Handler(Looper.getMainLooper()).post {
            val bitmap = createBitmapFromData()
            if (bitmap != null) {
                saveBitmapToGallery(bitmap)
            } else {
                Toast.makeText(this, "Lỗi khi tạo ảnh!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm tạo Bitmap từ dữ liệu pixel (không phụ thuộc vào GridView)
    private fun createBitmapFromData(): Bitmap? {
        // Tính kích thước dựa trên dữ liệu pixelArt
        val cellSize = pixelAdapter.cellSize
        if (cellSize <= 0) {
            return null // Tránh trường hợp cellSize không hợp lệ
        }

        val width = pixelArt[0].size * cellSize
        val height = pixelArt.size * cellSize

        if (width <= 0 || height <= 0) {
            return null
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Vẽ nền trắng
        canvas.drawColor(Color.WHITE)

        // Vẽ từng ô pixel dựa trên userPixels
        for (row in pixelArt.indices) {
            for (col in pixelArt[row].indices) {
                val pixelValue = userPixels[row][col]
                if (pixelValue != 0 && pixelValue <= colors.size) {
                    val color = colors[pixelValue - 1]
                    val paint = Paint().apply {
                        this.color = color
                        style = Paint.Style.FILL
                    }
                    val left = col * cellSize
                    val top = row * cellSize
                    canvas.drawRect(
                        left.toFloat(),
                        top.toFloat(),
                        (left + cellSize).toFloat(),
                        (top + cellSize).toFloat(),
                        paint
                    )
                }
            }
        }

        return bitmap
    }

    // Hàm lưu Bitmap vào thư viện ảnh
    private fun saveBitmapToGallery(bitmap: Bitmap) {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "PixelArt_$timeStamp.png"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/PixelArt")
            }
        }

        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }
                Toast.makeText(this, "Đã lưu ảnh vào thư viện!", Toast.LENGTH_SHORT).show()
            } ?: throw IOException("Không thể tạo file media")
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Lỗi khi lưu ảnh!", Toast.LENGTH_SHORT).show()
        } finally {
            bitmap.recycle()
        }
    }

    // Thêm hàm kiểm tra quyền
    private fun checkStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true // Không cần quyền trên Android 10+
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_CODE
                )
                false
            }
        }
    }

    // Thêm hằng số cho request code
    companion object {
        private const val STORAGE_PERMISSION_CODE = 1001
    }

    // Hàm kiểm tra hoàn thành tranh (không hiển thị dialog)
    private fun checkArtCompletion(): Boolean {
        for (i in pixelArt.indices) {
            for (j in pixelArt[i].indices) {
                if (pixelArt[i][j] != 0 && userPixels[i][j] == 0) {
                    return false
                }
            }
        }
        return true
    }

    // Xử lý kết quả yêu cầu quyền
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                savePixelArtToGallery()
            } else {
                Toast.makeText(
                    this,
                    "Quyền truy cập bộ nhớ bị từ chối, không thể lưu ảnh",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    inner class PixelAdapter : BaseAdapter() {
        var cellSize = 0

        override fun getCount(): Int = pixelArt.size * pixelArt[0].size

        override fun getItem(position: Int): Any = position

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val row = position / pixelArt[0].size
            val col = position % pixelArt[0].size
            val pixelValue = pixelArt[row][col]
            val isColored = userPixels[row][col] != 0

            val view = convertView ?: LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_pixel, parent, false)

            val pixelView = view.findViewById<View>(R.id.pixelView)
            val pixelNumber = view.findViewById<TextView>(R.id.pixelNumber)

            pixelView.layoutParams = FrameLayout.LayoutParams(cellSize, cellSize)

            if (pixelValue != 0 && pixelValue <= colors.size) {
                if (isColored && userPixels[row][col] <= colors.size) {
                    val color = colors[userPixels[row][col] - 1]
                    when (selectedBackgroundStyle) {
                        BackgroundStyle.PLAIN -> {
                            pixelView.setBackgroundColor(color)
                        }
                        BackgroundStyle.LEGO -> {
                            val drawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.lego_background)
                            drawable?.setTint(color)
                            pixelView.background = drawable
                        }
                        BackgroundStyle.CROSS -> {
                            val drawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.cross_background)
                            drawable?.setTint(color)
                            pixelView.background = drawable
                        }
                    }
                    pixelNumber.text = ""
                } else {
                    pixelView.setBackgroundColor(numberToGrayscale(pixelValue))
                    pixelNumber.text = pixelValue.toString()
                }

                view.setOnClickListener {
                    if (isMagicMode && pixelValue != 0) {
                        fillAllPixelsWithValue(pixelValue)
                    } else if (!isDragMode && !isDrawingMode && pixelValue != 0) {
                        if (pixelValue == selectedColorIndex + 1) {
                            userPixels[row][col] = pixelValue
                            notifyDataSetChanged()
                            updateProgress()
                            saveUserPixels()
                            if (isArtCompleted()) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Chúc mừng! Bạn đã hoàn thành bức tranh!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Bạn phải chọn màu số $pixelValue để tô ô này",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else if (pixelValue != 0) {
                Log.e("PixelAdapter", "Pixel value $pixelValue exceeds color count ${colors.size}")
                pixelView.setBackgroundColor(Color.LTGRAY)
                pixelNumber.text = "?"
                view.isEnabled = false
            } else {
                pixelView.setBackgroundColor(Color.WHITE)
                pixelNumber.text = ""
                view.isEnabled = false
            }

            return view
        }
    }
}