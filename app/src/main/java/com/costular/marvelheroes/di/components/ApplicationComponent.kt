package com.costular.marvelheroes.di.components

import android.arch.persistence.room.Database
import android.content.Context
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.LocalDataSource
import com.costular.marvelheroes.di.modules.ApplicationModule
import com.costular.marvelheroes.di.modules.DataModule
import com.costular.marvelheroes.di.modules.NetModule
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroeDetailActivity
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import com.costular.marvelheroes.presentation.util.Navigator
import com.costular.marvelheroes.presentation.util.mvvm.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getContext(): Context
    fun getRepository(): MarvelHeroesRepositoryImpl
    fun getHeroService(): MarvelHeroesService
    fun getNavigator(): Navigator
    fun getLocalDataSource(): LocalDataSource

    fun inject(heroesListActivity: HeroesListActivity)

    fun inject(marvelHeroeDetailActivity: MarvelHeroeDetailActivity)

}