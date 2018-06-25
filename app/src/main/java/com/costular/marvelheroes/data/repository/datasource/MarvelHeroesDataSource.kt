package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import io.reactivex.Observable

interface MarvelHeroesDataSource {

    fun getMarvelHeroesList(): Observable<List<MarvelHero>>

}