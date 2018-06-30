package com.costular.marvelheroes.data.repository.datasource

import android.os.Parcelable
import com.costular.marvelheroes.data.db.HeroesDatabase
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

class LocalDataSource(private val heroesDatabase: HeroesDatabase) {

    fun getLocalMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            heroesDatabase
                    .getHeroesDao()
                    .getAllHeroes()
                    .toFlowable()

        fun saveHeroes(heroes: List<MarvelHeroEntity>) {
            Observable.fromCallable {
                heroesDatabase.getHeroesDao().removeAndInsertHeroes(heroes)
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }

        fun updateHeroe(hero: MarvelHeroEntity) {
            Observable.fromCallable {
                heroesDatabase.getHeroesDao().updateHeroe(hero)
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }

}