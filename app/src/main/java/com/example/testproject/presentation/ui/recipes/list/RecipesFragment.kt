package com.example.testproject.presentation.ui.recipes.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.R
import com.example.testproject.core.App
import com.example.testproject.databinding.FragmentMenuBinding
import com.example.testproject.presentation.util.ViewModelFactory
import com.example.testproject.presentation.model.State
import com.example.testproject.presentation.ui.base.BaseFragment
import com.example.testproject.presentation.ui.recipes.list.adapters.*
import javax.inject.Inject

class RecipesFragment : BaseFragment<FragmentMenuBinding>(), OnRecipeClickListener,
    OnCategoryClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RecipesViewModel
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observe()
    }

    private fun initAdapter() {
        categoryAdapter = CategoryAdapter(this)
        val actionAdapter = ActionAdapter()
        recipesAdapter = RecipesAdapter(this)

        binding.rvAction.adapter = actionAdapter
        binding.rvCategory.adapter = categoryAdapter
        binding.rvMenu.adapter = recipesAdapter

        binding.rvAction.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMenu.layoutManager = LinearLayoutManager(requireContext())


        ContextCompat.getDrawable(requireContext(), R.drawable.divider_drawable)
            ?.let {
                val dividerItemDecoration =
                    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                dividerItemDecoration.setDrawable(it)
                binding.rvMenu.addItemDecoration(dividerItemDecoration)
            }

        actionAdapter.setData(listOf("A", "B", "C", "D", "E"))
    }

    private fun observe() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.setData(categories)
        }

        viewModel.recipes.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.loading.root.isVisible = true
                }
                is State.Error -> {
                    binding.loading.root.isVisible = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is State.Result -> {
                    binding.loading.root.isVisible = false
                    recipesAdapter.data = state.data
                }
            }
        }
    }

    override fun onCategory(id: Int, isSelected: Boolean) {
        if (isSelected) {
            viewModel.unselect(id)
        } else {
            viewModel.select(id)
        }
    }

    override fun onClick(id: Int) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.detailFragment, bundle)
    }
}