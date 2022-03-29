package com.example.phat_coding_assignment_3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import javax.sql.DataSource

class InventoryAdapter() : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {


    private val fruits = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
    )

    class InventoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruit_image)
        val fruitName: TextView = view.findViewById(R.id.fruit_name)
        val fruitAmount: TextView = view.findViewById(R.id.fruit_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.inventory_item, parent, false)

        return InventoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val fruit = fruits[position]

        // Binding data to view
        holder.fruitImage.setImageResource(R.drawable.apple)
        holder.fruitName.text = "Apple"
        holder.fruitAmount.text = "4"
    }

    override fun getItemCount(): Int {
        return fruits.size
    }
}