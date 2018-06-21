package com.costular.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.costular.marvelheroes.data.db.HeroesDatabase
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.LocalDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideHeroesDatabase(context: Context): HeroesDatabase =
            Room.databaseBuilder(context, HeroesDatabase::class.java, "heroes.db").build()

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSoruce(marvelHeroesService: MarvelHeroesService)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService)

    @Singleton
    @Provides
    fun provideLocalDataSource(heroesDatabase: HeroesDatabase): LocalDataSource =
            LocalDataSource(heroesDatabase)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            localDataSource: LocalDataSource,
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            marvelHeroMapper: MarvelHeroMapper): MarvelHeroesRepositoryImpl =
            MarvelHeroesRepositoryImpl(localDataSource, marvelRemoteMarvelHeroesDataSource, marvelHeroMapper)


}