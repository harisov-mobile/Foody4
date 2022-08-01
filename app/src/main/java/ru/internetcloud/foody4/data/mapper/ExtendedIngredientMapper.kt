package ru.internetcloud.foody4.data.mapper

import ru.internetcloud.foody4.data.entity.ExtendedIngredientDbModel
import ru.internetcloud.foody4.data.network.dto.ExtendedIngredientDTO
import ru.internetcloud.foody4.domain.model.ExtendedIngredient
import javax.inject.Inject

class ExtendedIngredientMapper @Inject constructor() {

    fun fromDomainToDbModel(extendedIngredient: ExtendedIngredient, recipeId: Int): ExtendedIngredientDbModel {
        return ExtendedIngredientDbModel(
            recipeId = recipeId,
            amount = extendedIngredient.amount,
            consistency = extendedIngredient.consistency,
            image = extendedIngredient.image,
            name = extendedIngredient.name,
            original = extendedIngredient.original,
            unit = extendedIngredient.unit
        )
    }

    fun fromDomainListToDbModelList(list: List<ExtendedIngredient>, recipeId: Int): List<ExtendedIngredientDbModel> {
        return list.map {
            fromDomainToDbModel(it, recipeId)
        }
    }

    fun fromDbModelListToDomainList(listDbModel: List<ExtendedIngredientDbModel>): List<ExtendedIngredient> {
        return listDbModel.map {
            fromDbModelToDomain(it)
        }
    }

    fun fromDbModelToDomain(extendedIngredientDbModel: ExtendedIngredientDbModel): ExtendedIngredient {
        return ExtendedIngredient(
            amount = extendedIngredientDbModel.amount,
            consistency = extendedIngredientDbModel.consistency,
            image = extendedIngredientDbModel.image,
            name = extendedIngredientDbModel.name,
            original = extendedIngredientDbModel.original,
            unit = extendedIngredientDbModel.unit
        )
    }

    fun fromDTOToDomain(extendedIngredientDTO: ExtendedIngredientDTO): ExtendedIngredient {
        return ExtendedIngredient(
            amount = extendedIngredientDTO.amount,
            consistency = extendedIngredientDTO.consistency,
            image = extendedIngredientDTO.image ?: "",
            name = extendedIngredientDTO.name,
            original = extendedIngredientDTO.original,
            unit = extendedIngredientDTO.unit
        )
    }

    fun fromDTOListToDomainList(list: List<ExtendedIngredientDTO>): List<ExtendedIngredient> {
        return list.map { fromDTOToDomain(it) }
    }
}
