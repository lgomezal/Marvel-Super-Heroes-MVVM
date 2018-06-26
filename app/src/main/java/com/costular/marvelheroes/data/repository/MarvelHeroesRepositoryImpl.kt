package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.datasource.FakeMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.LocalDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

class MarvelHeroesRepositoryImpl(private val localDataSource: LocalDataSource,
        private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val marvelHeroesMapper: MarvelHeroMapper)
    : MarvelHeroesRepository {



    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> =
            getHeroesFromDb().concatWith(getHeroesFromApi())

    private fun getHeroesFromDb(): Observable<List<MarvelHeroEntity>> =
            localDataSource.getLocalMarvelHeroesList()
                    .toObservable()

    private fun getHeroesFromApi(): Observable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource
                    .getMarvelHeroesList()
                    .map { marvelHeroesMapper.transformList(it) }
                    .doOnNext { localDataSource.saveHeroes(it) }
}