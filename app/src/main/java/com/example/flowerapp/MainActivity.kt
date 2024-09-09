package com.example.flowerapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerapp.adapter.FlowerAdapter
import com.example.flowerapp.databinding.ActivityMainBinding
import com.example.flowerapp.model.Flower

class MainActivity : AppCompatActivity() {
     private  lateinit var binding: ActivityMainBinding
     private  lateinit var  flowerAdapter: FlowerAdapter
     val flower =ArrayList<Flower>()


    override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         binding.flowerRv.layoutManager = LinearLayoutManager(this)
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.cherry,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))
        flower.add(Flower("Rose",20,4.0,R.drawable.mango,"Rose"))

        flowerAdapter = FlowerAdapter(flower)
        binding.flowerRv.adapter = flowerAdapter


        flowerAdapter.onClick={
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("flower",it.flowerName)
            intent.putExtra("price",it.flowerPrice)
            intent.putExtra("quantity",it.flowerQnt)
            intent.putExtra("desc",it.flowerDesc)
            intent.putExtra("image",it.flowerImg)

            startActivity(intent)
        }
        binding.addBtn.setOnClickListener{
            showFlowerAddDialog()
        }


    }

     private  fun showFlowerAddDialog(){
         val dialogView = layoutInflater.from(this).inflate(R.layout.add_flower,null)
         val nameEt = dialogView.findViewById<EditText>(R.id.flowerNameEt)
         val priceEt = dialogView.findViewById<EditText>(R.id.flowerPriceEt)
         val qntEt = dialogView.findViewById<EditText>(R.id.flowerQntEt)
         val descEt = dialogView.findViewById<EditText>(R.id.flowerDescEt)


         AlertDialog.Builder(this)
             .setTitle("Add Flower")
             .setView(dialogView)
             .setPositiveButton("Add") { _, _ ->
                 val name = nameEt.text.toString()
                 val price = priceEt.text.toString().toDouble()
                 val qnt = qntEt.text.toString().toInt()
                 val desc = descEt.text.toString()
                 val img = R.drawable.cherry
                 flower.add(flower(name, qnt, price, img, desc))
                 flowerAdapter.notifyItemInserted(flower.size - 1)
             }
             .setNegativeButton("Cancel", null)
             .show()
     }
}