package com.example.testproject.domain.usecase

import com.example.testproject.domain.entity.RecipeDetail
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.domain.repository.ProductRepository
import com.example.testproject.presentation.model.RecipeDetailItem
import com.example.testproject.presentation.util.NetworkManager
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetInformationRecipeUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val networkManager: NetworkManager
) {

    fun execute(id: Int): Single<ResultData<RecipeDetailItem>> {
        if (networkManager.isConnected().value) {
            return productRepository.getInformationRecipe(id).map { result ->
                when (result) {
                    is ResultData.Success -> {
                        productRepository.saveRecipe(result.data).subscribe()
                        ResultData.Success(
                            mapToDetailItem(result.data)
                        )
                    }
                    is ResultData.Error -> {
                        result
                    }
                }
            }
        } else {
            return productRepository.getRecipes(id).map { result ->
                if (result.isNotEmpty()) {
                    ResultData.Success(
                        mapToDetailItem(result[0])
                    )
                } else {
                    ResultData.Error("don't found")
                }

            }
        }
    }

    private fun mapToDetailItem(result: RecipeDetail): RecipeDetailItem {
        return RecipeDetailItem(
            result.id,
            result.title,
            result.image,
            result.summary,
            result.readyInMinutes
        )
    }
}