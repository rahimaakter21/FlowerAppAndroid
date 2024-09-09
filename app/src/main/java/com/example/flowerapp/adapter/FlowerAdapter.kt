package com.example.flowerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerapp.databinding.FlowerItemLayoutBinding
import com.example.flowerapp.model.Flower

class FlowerAdapter(private  val flowerList:ArrayList<Flower>):RecyclerView.Adapter<FlowerAdapter.MyViewHolder>(){
   var onClick:((Flower)->Unit)?=null
    class MyViewHolder( val binding: FlowerItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = FlowerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            flowerName.text = flowerList[position].flowerName
            flowerQnt.text = "Quantity: " + flowerList[position].flowerQnt.toString()
            flowerPrice.text = "Price: $" + flowerList[position].flowerPrice.toString()
            flowerImg.setImageResource(flowerList[position].flowerImg)
        }

        holder.itemView.setOnClickListener {
             onClick?.invoke(flowerList[position])

        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Flower Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { _, _ ->
                    flowerList.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }
}