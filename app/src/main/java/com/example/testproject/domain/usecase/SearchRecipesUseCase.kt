package com.example.testproject.domain.usecase

import com.example.testproject.core.Const.API_KEY
import com.example.testproject.domain.entity.Recipe
import com.example.testproject.domain.entity.ResultData
import com.example.testproject.domain.repository.ProductRepository
import com.example.testproject.presentation.model.RecipeItem
import com.example.testproject.presentation.util.NetworkManager
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val networkManager: NetworkManager
) {

    fun execute(searchQuery: String): Single<ResultData<List<RecipeItem>>> {
        if (networkManager.isConnected().value) {
            val queries: HashMap<String, String> = HashMap()
            queries["apiKey"] = API_KEY
            queries["number"] = "25"
            queries["cuisine"] = searchQuery

            return productRepository.searchRecipes(queries).map { result ->
                when (result) {
                    is ResultData.Success -> {
                        productRepository.saveRecipes(result.data).subscribe()
                        ResultData.Success(result.data.map { recipe ->
                            mapToRecipeItem(recipe)
                        })
                    }
                    is ResultData.Error -> {
                        result
                    }
                }
            }
        } else {
            return productRepository.getRecipes().map { recipes ->
                ResultData.Success(recipes.map { recipe ->
                    mapToRecipeItem(recipe)
                } ) as ResultData<List<RecipeItem>>
            }
        }
    }

    private fun mapToRecipeItem(recipe: Recipe): RecipeItem {
        return RecipeItem(
            recipe.id,
            recipe.title,
            recipe.image,
            recipe.summary
        )
    }
}