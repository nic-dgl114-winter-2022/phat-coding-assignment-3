package com.example.phat_coding_assignment_3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.databinding.MarketItemBinding

class MarketAdapter(private val onItemClicked: (Fruit, String) -> Unit) :
    ListAdapter<Fruit, MarketAdapter.MarketViewHolder>(DiffCallback) {
    class MarketViewHolder(
        private var binding: MarketItemBinding,
        private var onItemClicked: (Fruit, String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit) {
            binding.fruitImage.setImageResource(fruit.fruitImageResourceId)
            binding.fruitName.text = fruit.fruitName
            binding.availableAmount.text = fruit.fruitQuantityInStock.toString()
            binding.fruitPrice.text = fruit.fruitPrice.toString()

            // Can sell
            if (fruit.fruitQuantityInStock > 0) {
                binding.sellButton.isEnabled = true
                binding.fruitSellAmount.isEnabled = true
                binding.sellButton.setOnClickListener {
                    // Check if amount is valid or not
                    if (binding.fruitSellAmount.text.toString() != "" && binding.fruitSellAmount.text.toString() != "0") {
                        onItemClicked(fruit, binding.fruitSellAmount.text.toString())
                    }
                    // Reset input
                    binding.fruitSellAmount.setText("")
                }
            } else {
                // Out of stock
                binding.sellButton.isEnabled = false
                binding.fruitSellAmount.isEnabled = false
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketViewHolder {
        val layout =
            MarketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MarketViewHolder(layout, onItemClicked)
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