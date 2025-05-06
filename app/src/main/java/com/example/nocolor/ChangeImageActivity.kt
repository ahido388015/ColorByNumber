package com.example.nocolor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChangeImageActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var selectedBitmap: Bitmap? = null

    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri ->
        uri?.let {
            val inputStream = contentResolver.openInputStream(uri)
            selectedBitmap = BitmapFactory.decodeStream(inputStream)
            imageView.setImageBitmap(selectedBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_image)

        imageView = findViewById(R.id.imageView)

        val editPixelSize = findViewById<EditText>(R.id.editPixelSize)
        val editColorCount = findViewById<EditText>(R.id.editColorCount)
        val buttonChoose = findViewById<Button>(R.id.buttonChooseImage)
        val buttonPixelate = findViewById<Button>(R.id.buttonPixelate)

        buttonChoose.setOnClickListener {
            imagePicker.launch("image/*")
        }

        buttonPixelate.setOnClickListener {
            val pixelSize = editPixelSize.text.toString().toIntOrNull() ?: 10
            val colorCount = editColorCount.text.toString().toIntOrNull() ?: 16

            selectedBitmap?.let {
                val pixelatedBitmap = PixelUtils.pixelate(it, pixelSize, colorCount)
                imageView.setImageBitmap(pixelatedBitmap)
            }
        }

//        val buttonSaveImage = findViewById<Button>(R.id.buttonSaveImage)

//        buttonSaveImage.setOnClickListener {
//            selectedBitmap?.let {
//                val pixelSize = editPixelSize.text.toString().toIntOrNull() ?: 10
//                val colorCount = editColorCount.text.toString().toIntOrNull() ?: 16
//
//                val pixelatedBitmap = PixelUtils.pixelate(it, pixelSize, colorCount)
//                saveImageToGallery(pixelatedBitmap)
//                imageView.setImageBitmap(pixelatedBitmap)
//            } ?: run {
//                Toast.makeText(this, "Vui lòng chọn ảnh trước!", Toast.LENGTH_SHORT).show()
//            }
//        }

    }



//    fun saveBitmapToGallery(context: Context, bitmap: Bitmap, filename: String = "pixel_image_${System.currentTimeMillis()}.png"): Boolean {
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
//            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
//            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/PixelatedImages") // Android 10+
//            put(MediaStore.Images.Media.IS_PENDING, 1)
//        }
//
//        val contentResolver = context.contentResolver
//        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//
//        uri?.let {
//            val outputStream: OutputStream? = contentResolver.openOutputStream(it)
//            outputStream.use { stream ->
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            }
//
//            contentValues.clear()
//            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
//            contentResolver.update(uri, contentValues, null, null)
//
//            return true
//        }
//
//        return false
//    }

}