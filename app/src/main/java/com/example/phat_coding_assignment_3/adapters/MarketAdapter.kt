package com.example.phat_coding_assignment_3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R

class MarketAdapter() : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    private val fruits = listOf(
        1,
        2,
        3,
        6,
    )

    class MarketViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruit_image)
        val fruitName: TextView = view.findViewById(R.id.fruit_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.market_item, parent, false)

        return MarketViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val fruit = fruits[position]

        // Binding data to view
        holder.fruitImage.setImageResource(R.drawable.apple)
        holder.fruitName.text = "Apple"

    }

    override fun getItemCount(): Int {
        return fruits.size
    }
}