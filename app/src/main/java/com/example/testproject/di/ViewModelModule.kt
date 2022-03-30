package com.example.testproject.di

import androidx.lifecycle.ViewModel
import com.example.testproject.presentation.ui.recipes.detail.DetailViewModel
import com.example.testproject.presentation.ui.recipes.list.RecipesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel::class)
    fun bindMenuViewModel(viewModel: RecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}