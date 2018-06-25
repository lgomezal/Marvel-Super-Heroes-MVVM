package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.net.MarvelHeroesService
import io.reactivex.Observable

class RemoteMarvelHeroesDataSource(private val marvelHeroesService: MarvelHeroesService):
        MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Observable<List<MarvelHero>> =
            marvelHeroesService.getMarvelHeroesList().map { it.superheroes }

}