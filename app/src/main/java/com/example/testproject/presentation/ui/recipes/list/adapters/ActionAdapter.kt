package com.example.testproject.presentation.ui.recipes.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.ItemActionBinding

class ActionAdapter : RecyclerView.Adapter<ActionAdapter.ActionViewHolder>() {

    private var data = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        return ActionViewHolder(
            ItemActionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<String>) {
        data = newData
        notifyDataSetChanged()
    }

    class ActionViewHolder(private val binding: ItemActionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String) {

        }
    }
}