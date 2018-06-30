package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import java.util.*
import javax.inject.Inject

class MarvelHeroeDetailViewModel @Inject constructor (private val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl): BaseViewModel() {

    fun updateHero(hero: MarvelHeroEntity) {
        marvelHeroesRepositoryImpl.updateHeroeFromView(hero)
    }

}