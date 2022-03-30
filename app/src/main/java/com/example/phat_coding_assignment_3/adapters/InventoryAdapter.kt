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

    class InventoryViewHolder(private var binding: InventoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit) {
            binding.fruitImage.setImageResource(fruit.fruitImageResourceId)
            binding.fruitName.text = fruit.fruitName
            binding.fruitAmount.text = fruit.fruitQuantityInStock.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val layout =
            InventoryItemBinding.inflate(LayoutInflater.from(parent.context))

        return InventoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val current = getItem(position)

        holder.bind(current)
    }

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