package com.example.testproject.presentation.ui.recipes.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testproject.databinding.ItemProductBinding
import com.example.testproject.presentation.model.RecipeItem

class RecipesAdapter(private val onRecipeClickListener: OnRecipeClickListener) : RecyclerView.Adapter<RecipesAdapter.ProductViewHolder>() {

    private val differ = AsyncListDiffer(this, RecipesDiffUtil())

    var data: List<RecipeItem>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onRecipeClickListener
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ProductViewHolder(private val binding: ItemProductBinding, private val onRecipeClickListener: OnRecipeClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeItem) {
            binding.tvTitle.text = recipe.title
            Glide.with(binding.root.context).load(recipe.image).into(binding.ivPicture)
            binding.ivPicture.setOnClickListener {
                onRecipeClickListener.onClick(recipe.id)
            }
        }
    }
}

interface OnRecipeClickListener {
    fun onClick(id: Int)
}