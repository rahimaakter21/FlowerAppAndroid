package com.example.flowerapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
        setContentView(binding.root)

         binding.flowerRv.layoutManager = LinearLayoutManager(this)
        flower.add(Flower("Rose",20,4.0,R.drawable.rose,"Rose"))
        flower.add(Flower("Lotus",20,4.0,R.drawable.lotus,"Lotus"))
        flower.add(Flower("Jasmine",20,4.0,R.drawable.jasmine,"Jasmine"))
        flower.add(Flower("Daisy",20,4.0,R.drawable.daisy,"Daisy"))
        flower.add(Flower("Tulip",20,4.0,R.drawable.tulip,"Tulip"))
        flower.add(Flower("ButterflyPea",20,4.0,R.drawable.butterflypea,"ButterflyPea"))
        flower.add(Flower("ChinaRose",20,4.0,R.drawable.chinarose,"ChinaRose"))
        flower.add(Flower("Orchid",20,4.0,R.drawable.orchid,"Orchid"))
        flower.add(Flower("Tulip",20,4.0,R.drawable.tulip,"Tulip"))
        flower.add(Flower("Lily",20,4.0,R.drawable.lily,"Lily"))
        flower.add(Flower("Rose",20,4.0,R.drawable.rose,"Rose"))
        flower.add(Flower("Lotus",20,4.0,R.drawable.lotus,"Lotus"))
        flower.add(Flower("Jasmine",20,4.0,R.drawable.jasmine,"Jasmine"))
        flower.add(Flower("Daisy",20,4.0,R.drawable.daisy,"Daisy"))
        flower.add(Flower("Orchid",20,4.0,R.drawable.orchid,"Orchid"))
        flower.add(Flower("ButterflyPea",20,4.0,R.drawable.butterflypea,"ButterflyPea"))
        flower.add(Flower("ChinaRose",20,4.0,R.drawable.chinarose,"ChinaRose"))
        flower.add(Flower("Orchid",20,4.0,R.drawable.orchid,"Orchid"))
        flower.add(Flower("Tulip",20,4.0,R.drawable.tulip,"Tulip"))
        flower.add(Flower("Lily",20,4.0,R.drawable.lily,"Lily"))



        flowerAdapter = FlowerAdapter(flower)
        binding.flowerRv.adapter = flowerAdapter


        flowerAdapter.onClick={
            val intent = Intent(this,Activity_details::class.java)
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

         val itemTouchHelper= ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(

             0,
             ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

         ){
             override  fun onMove(
                 recyclerView: RecyclerView,
                 viewHolder: RecyclerView.ViewHolder,
                 target:RecyclerView.ViewHolder
             ):Boolean{
                 return  false
             }
             override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                 val position = viewHolder.adapterPosition
                 flower.removeAt(position)
                 flowerAdapter.notifyItemRemoved(position)
             }
         })
         itemTouchHelper.attachToRecyclerView(binding.flowerRv)


    }

     private  fun showFlowerAddDialog(){
         val dialogView = layoutInflater.inflate(R.layout.add_flower,null)
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
                 val img = R.drawable.lily
                 flower.add(Flower(name, qnt, price, img, desc))
                 flowerAdapter.notifyItemInserted(flower.size - 1)
             }
             .setNegativeButton("Cancel", null)
             .show()
     }
}