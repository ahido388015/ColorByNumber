package com.example.nocolor

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.widget.ImageButton
import android.widget.ImageView

@Parcelize
data class PixelArtParcelable(
    val id: Int,
    val title: String,
    val category: String,
    val pixelData: ArrayList<IntArray>,
    val colors: ArrayList<Int>
) : Parcelable {
    companion object {
        fun fromPixelArt(pixelArt: PixelArt): PixelArtParcelable {
            return PixelArtParcelable(
                id = pixelArt.id,
                title = pixelArt.title,
                category = pixelArt.category,
                pixelData = ArrayList(pixelArt.pixelData.map { it.clone() }),
                colors = ArrayList(pixelArt.colors)
            )
        }
    }

    fun toPixelArt(): PixelArt {
        return PixelArt(
            id = id,
            title = title,
            category = category,
            pixelData = pixelData.toTypedArray(),
            colors = colors.toList()
        )
    }
}
class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)

        val changeImageButton = findViewById<ImageButton>(R.id.ChangeImageButton)
        changeImageButton.setOnClickListener {
            val intent = Intent(this, ChangeImageActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoryAdapter(PixelArtData.getCategories())
    }

    inner class CategoryAdapter(private val categories: List<String>) :
        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val categoryTitle: TextView = itemView.findViewById(R.id.categoryTitle)
            val pixelArtRecyclerView: RecyclerView = itemView.findViewById(R.id.pixelArtRecyclerView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
            return CategoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = categories[position]
            holder.categoryTitle.text = category

            val pixelArts = PixelArtData.getPixelArtsByCategory(category)
            holder.pixelArtRecyclerView.layoutManager = LinearLayoutManager(
                holder.itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            holder.pixelArtRecyclerView.adapter = PixelArtAdapter(pixelArts)
        }

        override fun getItemCount(): Int = categories.size
    }

    inner class PixelArtAdapter(private val pixelArts: List<PixelArt>) :
        RecyclerView.Adapter<PixelArtAdapter.PixelArtViewHolder>() {

        inner class PixelArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val pixelArtTitle: TextView = itemView.findViewById(R.id.pixelArtTitle)
            val pixelArtPreview: ImageView = itemView.findViewById(R.id.pixelArtPreview)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixelArtViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pixel_art, parent, false)
            return PixelArtViewHolder(view)
        }

        // Tạo bitmap preview từ dữ liệu pixel
        override fun onBindViewHolder(holder: PixelArtViewHolder, position: Int) {
            val pixelArt = pixelArts[position]
            holder.pixelArtTitle.text = pixelArt.title
            val previewBitmap = createPixelArtPreview(pixelArt.pixelData, pixelArt.colors)
            holder.pixelArtPreview.setImageBitmap(previewBitmap)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, MainActivity::class.java)
                intent.putExtra("PIXEL_ART", PixelArtParcelable.fromPixelArt(pixelArt))
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = pixelArts.size

        private fun createPixelArtPreview(pixelData: Array<IntArray>, colors: List<Int>): Bitmap {
            val size = pixelData.size
            val scaleFactor = 100 / size
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // Vẽ từng pixel dạng grayscale
            for (i in pixelData.indices) {
                for (j in pixelData[i].indices) {
                    val paint = Paint().apply {
                        color = when (pixelData[i][j]) {
                            1 -> Color.rgb(120, 120, 120)
                            2 -> Color.rgb(140, 140, 140)
                            3 -> Color.rgb(180, 180, 180)
                            4 -> Color.rgb(220, 220, 180)
                            5 -> Color.rgb(190, 190, 190)
                            6 -> Color.rgb(150, 150, 150)
                            else -> Color.WHITE
                        }
                    }
                    canvas.drawRect(
                        j.toFloat(),
                        i.toFloat(),
                        (j + 1).toFloat(),
                        (i + 1).toFloat(),
                        paint
                    )
                }
            }

            // Scale lại, tắt antilias vưới lọc
            val scaledBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
            val scaledCanvas = Canvas(scaledBitmap)
            val paint = Paint().apply {
                isAntiAlias = false
                isFilterBitmap = false
            }
            scaledCanvas.drawBitmap(bitmap, null, Rect(0, 0, 100, 100), paint)

            return scaledBitmap
        }
    }
}