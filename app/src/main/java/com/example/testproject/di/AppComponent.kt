package com.example.testproject.di

import android.content.Context
import com.example.testproject.presentation.ui.MainActivity
import com.example.testproject.presentation.ui.recipes.detail.DetailFragment
import com.example.testproject.presentation.ui.recipes.list.RecipesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, ViewModelModule::class, DataModule::class, NetworkModule::class, DatabaseModule::class])
interface  AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: RecipesFragment)
    fun inject(fragment: DetailFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}