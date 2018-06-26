package com.costular.marvelheroes.presentation.util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import com.costular.marvelheroes.data.repository.datasource.LocalDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroeDetailActivity

class Navigator {

    fun goToHeroDetail(activity: Activity, hero: MarvelHeroEntity, image: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image,
                ViewCompat.getTransitionName(image))
        val intent = Intent(activity, MarvelHeroeDetailActivity::class.java).apply {
            putExtra(MarvelHeroeDetailActivity.PARAM_HEROE, hero)
        }

        activity.startActivity(intent, options.toBundle())
    }

}