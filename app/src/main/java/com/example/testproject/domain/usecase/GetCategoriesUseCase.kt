package com.example.testproject.domain.usecase

import com.example.testproject.domain.repository.ProductRepository
import com.example.testproject.presentation.model.CategoryItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val productRepository: ProductRepository) {

    fun execute(): Single<List<CategoryItem>> =
        productRepository.getCategories().map { categories ->
            categories.map { category -> CategoryItem(category.id, category.title) }
        }
}