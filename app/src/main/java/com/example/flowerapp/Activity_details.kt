package com.example.flowerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flowerapp.databinding.ActivityDetailsBinding

class Activity_details : AppCompatActivity() {
    private  lateinit var  binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityDetailsBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val name=intent.getStringExtra("name")
        val price=intent.getDoubleExtra("price",0.0)
        val qnt=intent.getIntExtra("quantity",0)
        val img=intent.getIntExtra("img",0)
        val desc=intent.getStringExtra("desc")

        binding.apply {
            flowerName.text = name
            flowerPrice.text = "Price: $"+price.toString()
            flowerQnt.text = "Quantity: ".toString()
            flowerImg.setImageResource(img)
            flowerDesc.text = desc
        }

    }
}