package com.example.testproject.presentation.ui.recipes.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testproject.core.App
import com.example.testproject.databinding.FragmentDetailBinding
import com.example.testproject.presentation.util.ViewModelFactory
import com.example.testproject.presentation.model.RecipeDetailItem
import com.example.testproject.presentation.model.State
import com.example.testproject.presentation.ui.base.BaseFragment
import org.jsoup.Jsoup
import javax.inject.Inject


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        id?.let {
            viewModel.getCategories(it)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.recipe.observe(viewLifecycleOwner) { state ->
            when(state) {
                is State.Loading -> {
                    binding.loading.root.isVisible = true
                }
                is State.Error -> {
                    binding.loading.root.isVisible = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is State.Result -> {
                    binding.loading.root.isVisible = false
                    setData(state.data)
                }
            }
        }
    }

    private fun setData(recipeDetailItem: RecipeDetailItem) {
        binding.timeTextView.text = recipeDetailItem.readyInMinutes.toString()
        binding.summaryTextView.text = Jsoup.parse(recipeDetailItem.summary).text()
        Glide.with(binding.root.context).load(recipeDetailItem.image).into(binding.mainImageView)
    }

    companion object {

        fun newInstance() = DetailFragment()
    }
}