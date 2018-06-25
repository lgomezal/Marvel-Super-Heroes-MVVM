package com.costular.marvelheroes.data.net

import com.costular.marvelheroes.data.model.MarvelHeroesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MarvelHeroesService {

    @GET(".")
    fun getMarvelHeroesList(): Observable<MarvelHeroesResponse>

}