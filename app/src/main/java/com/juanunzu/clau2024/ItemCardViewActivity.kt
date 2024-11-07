package com.juanunzu.clau2024

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.juanunzu.clau2024.databinding.ActivityItemCardViewBinding

class ItemCardViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemCardViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemCardViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setupItemCardView()
    }

    private fun ActivityItemCardViewBinding.setupItemCardView() {
        itemCardView.apply {
            title = "Botas de viaje"
            subtitle = "Llegan hoy"
            image = ResourcesCompat.getDrawable(resources, R.drawable.botas_image, null)
            onFavoriteClick = View.OnClickListener {
                Toast.makeText(applicationContext, "added to favorites", Toast.LENGTH_SHORT).show()
            }
            onCartClick = View.OnClickListener {
                Toast.makeText(applicationContext, "added to cart", Toast.LENGTH_SHORT).show()
            }
            onInfoClick = View.OnClickListener {
                Toast.makeText(applicationContext, "clicked in more info", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
