package com.example.phat_coding_assignment_3.adapters

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
import com.example.phat_coding_assignment_3.databinding.MarketItemBinding

class MarketAdapter() : ListAdapter<Fruit, MarketAdapter.MarketViewHolder>(DiffCallback) {
    class MarketViewHolder(private var binding: MarketItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit) {
            if (fruit.fruitQuantityInStock > 0) {
                binding.fruitImage.setImageResource(fruit.fruitImageResourceId)
                binding.fruitName.text = fruit.fruitName
                binding.availableAmount.text = fruit.fruitQuantityInStock.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val layout =
            MarketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MarketViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
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