package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

interface MarvelHeroesRepository {

    fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>>

}