package com.example.mycocktailsapp.ui.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktailsapp.R
import com.example.mycocktailsapp.databinding.CocktailViewItemBinding
import com.example.mycocktailsapp.model.Drink
import com.squareup.picasso.Picasso

class CocktailAdapter(
    private val itemSet: MutableList<Drink> = mutableListOf(),
    private val onItemClick: (Drink) -> Unit
) :  RecyclerView.Adapter<CocktailViewHolder>() {

    fun updateItems(newItems: List<Drink>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder =
        CocktailViewHolder(
            CocktailViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class CocktailViewHolder(
    private val binding: CocktailViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Drink, onItemClick: (Drink) -> Unit) {
        binding.cocktailImageItem.setImageResource(R.drawable.ic_launcher_foreground)
        binding.cocktailNameItem.text = item.strDrink
        Log.d(TAG, "TEST2: ${item.strDrink}${item.strDrinkThumb}")

        Picasso.get()
            .load(item.strDrinkThumb)
            .placeholder(R.drawable.cocktail_app)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.cocktailImageItem);

        itemView.setOnClickListener {
            onItemClick(item)

        }

    }
}