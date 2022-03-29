package com.example.phat_coding_assignment_3.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.databinding.InventoryItemBinding

class InventoryAdapter() : ListAdapter<Fruit, InventoryAdapter.InventoryViewHolder>(DiffCallback) {

    private val fruits = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
    )

    class InventoryViewHolder(private var binding: InventoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        val fruitImage: ImageView = view.findViewById(R.id.fruit_image)
//        val fruitName: TextView = view.findViewById(R.id.fruit_name)
//        val fruitAmount: TextView = view.findViewById(R.id.fruit_amount)
        fun bind() {
            binding.fruitImage.setImageResource(R.drawable.apple)
            binding.fruitName.text = "Apple"
            binding.fruitAmount.text = "2"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val layout =
            InventoryItemBinding.inflate(LayoutInflater.from(parent.context))

        return InventoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        var fruit = fruits[position]

        // Binding data to view
//        fruit.fruitImage =
//        fruit.fruitName = "Apple"
//        fruit.fruitAmount = "2"
        holder.bind()
    }

//    override fun getItemCount(): Int {
//        return fruits.size
//    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Fruit>() {
            override fun areItemsTheSame(oldFruit: Fruit, newFruit: Fruit): Boolean {
                return oldFruit === newFruit
            }

            override fun areContentsTheSame(oldFruit: Fruit, newFruit: Fruit): Boolean {
                return oldFruit.fruitName == newFruit.fruitName
            }
        }
    }
}