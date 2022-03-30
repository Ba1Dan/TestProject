package com.example.testproject.di

import com.example.testproject.data.repository.ProductRepositoryImpl
import com.example.testproject.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module


@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: ProductRepositoryImpl): ProductRepository

}