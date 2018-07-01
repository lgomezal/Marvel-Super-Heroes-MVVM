package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
        private val getMarvelHeroesList: GetMarvelHeroesList) : BaseViewModel() {

    @Inject
    lateinit var marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl

    val heroesListState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoagingState: MutableLiveData<Boolean> = MutableLiveData()

    fun loadMarvelHeroes() {
        getMarvelHeroesList.marvelHeroesRepositoryImpl.getMarvelHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoagingState.postValue(true) }
                .doOnTerminate { isLoagingState.postValue(false) }
                .subscribeBy (
                        onNext = {
                            heroesListState.value = it
                        },
                        onError = {

                        }
                )
                .addTo(compositeDisposable)

    }

    fun updateHero(hero: MarvelHeroEntity) {
        marvelHeroesRepositoryImpl.updateHeroeFromView(hero)
    }

}