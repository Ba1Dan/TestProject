package com.example.testproject.presentation.ui.recipes.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.R
import com.example.testproject.databinding.ItemCategoryBinding
import com.example.testproject.presentation.model.CategoryItem

class CategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var data = emptyList<CategoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCategoryClickListener
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<CategoryItem>) {
        data = newData
        notifyDataSetChanged()
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding, private val onCategoryClickListener: OnCategoryClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryItem) {
                       binding.tvTitle.text = category.title
            if (category.isSelected) {
                binding.card.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.red))
                binding.tvTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.pink))
            } else {
                binding.card.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            }
            binding.tvTitle.setOnClickListener{
                onCategoryClickListener.onCategory(category.id, category.isSelected)
            }
        }
    }
}

interface OnCategoryClickListener {
    fun onCategory(id: Int, isSelected: Boolean)
}