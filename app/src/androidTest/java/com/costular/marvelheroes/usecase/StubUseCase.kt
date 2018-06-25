package com.costular.marvelheroes.usecase

import com.costular.marvelheroes.domain.usecase.UseCase
import io.reactivex.Observable

class StubUseCase : UseCase<IntArray>() {

    companion object {
        val intArray = intArrayOf(1, 2, 3, 4, 5, 6)
    }

    override fun buildCase(): Observable<IntArray> {
        return Observable.fromArray(intArray)
    }

}