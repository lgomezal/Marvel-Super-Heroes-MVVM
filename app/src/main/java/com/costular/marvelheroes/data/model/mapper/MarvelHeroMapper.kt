package com.costular.marvelheroes.data.model.mapper

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

class MarvelHeroMapper : Mapper<MarvelHero, MarvelHeroEntity> {

    override fun transform(input: MarvelHero): MarvelHeroEntity =
            MarvelHeroEntity(
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    input.isFavourite,
                    input.review,
                    input.reviewText,
                    getGroupsFromMarvelHero(input))

    override fun transformList(inputList: List<MarvelHero>): List<MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHero(marvelHero: MarvelHero): ArrayList<String> {
        val array = marvelHero.groups.replace("\\s".toRegex(), "").split(",").toTypedArray()

        return array.toCollection(ArrayList<String>())

    }

}