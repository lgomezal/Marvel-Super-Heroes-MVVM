package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.di.components.DaggerGetMarvelHeroesListComponent
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.MainApp
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

typealias ClickButton = (MarvelHeroEntity) -> Unit

class MarvelHeroeDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var marvelHeroeDetailViewModel: MarvelHeroeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        setUpViewModel()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let { fillHeroData(it) }

    }

    fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun setUpViewModel() {
        marvelHeroeDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MarvelHeroeDetailViewModel::class.java)
    }

    private fun fillHeroData(hero: MarvelHeroEntity) {
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        heroDetailName.text = hero.name
        heroDetailRealName.text = hero.realName
        heroDetailHeight.text = hero.height
        heroDetailPower.text = hero.power
        heroDetailAbilities.text = hero.abilities
        putStarsReview(hero.review)
        heroeObservations.text = SpannableStringBuilder(hero.reviewText)

        review1.setOnClickListener {
            hero.review = 1
            putStarsReview(hero.review)
            if (review1.background.constantState == ContextCompat.getDrawable(this, R.drawable.star_yellow)?.constantState) {
                hero.review = 1
                hero.reviewText = heroeObservations.text.toString()
            } else {
                hero.review = 0
                hero.reviewText = ""
            }
            marvelHeroeDetailViewModel.updateHero(hero)
        }

        review2.setOnClickListener {
            hero.review = 2
            hero.reviewText = heroeObservations.text.toString()
            putStarsReview(hero.review)
            marvelHeroeDetailViewModel.updateHero(hero)
        }

        review3.setOnClickListener {
            hero.review = 3
            hero.reviewText = heroeObservations.text.toString()
            putStarsReview(hero.review)
            marvelHeroeDetailViewModel.updateHero(hero)
        }

        review4.setOnClickListener {
            hero.review = 4
            hero.reviewText = heroeObservations.text.toString()
            putStarsReview(hero.review)
            marvelHeroeDetailViewModel.updateHero(hero)
        }

        review5.setOnClickListener {
            hero.review = 5
            hero.reviewText = heroeObservations.text.toString()
            putStarsReview(hero.review)
            marvelHeroeDetailViewModel.updateHero(hero)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun putStarsReview(review: Int) {
        if (review == 0) {
            review1.setBackgroundResource(R.drawable.star_grey)
            review2.setBackgroundResource(R.drawable.star_grey)
            review3.setBackgroundResource(R.drawable.star_grey)
            review4.setBackgroundResource(R.drawable.star_grey)
            review5.setBackgroundResource(R.drawable.star_grey)
        }

        if (review == 1) {
            if ((review1.background.constantState == ContextCompat.getDrawable(this, R.drawable.star_yellow)?.constantState) &&
                    (review2.background.constantState == ContextCompat.getDrawable(this, R.drawable.star_grey)?.constantState)) {
                review1.setBackgroundResource(R.drawable.star_grey)
            } else {
                review1.setBackgroundResource(R.drawable.star_yellow)
            }
            review2.setBackgroundResource(R.drawable.star_grey)
            review3.setBackgroundResource(R.drawable.star_grey)
            review4.setBackgroundResource(R.drawable.star_grey)
            review5.setBackgroundResource(R.drawable.star_grey)
        }

        if (review == 2) {
            review1.setBackgroundResource(R.drawable.star_yellow)
            review2.setBackgroundResource(R.drawable.star_yellow)
            review3.setBackgroundResource(R.drawable.star_grey)
            review4.setBackgroundResource(R.drawable.star_grey)
            review5.setBackgroundResource(R.drawable.star_grey)
        }

        if (review == 3) {
            review1.setBackgroundResource(R.drawable.star_yellow)
            review2.setBackgroundResource(R.drawable.star_yellow)
            review3.setBackgroundResource(R.drawable.star_yellow)
            review4.setBackgroundResource(R.drawable.star_grey)
            review5.setBackgroundResource(R.drawable.star_grey)
        }

        if (review == 4) {
            review1.setBackgroundResource(R.drawable.star_yellow)
            review2.setBackgroundResource(R.drawable.star_yellow)
            review3.setBackgroundResource(R.drawable.star_yellow)
            review4.setBackgroundResource(R.drawable.star_yellow)
            review5.setBackgroundResource(R.drawable.star_grey)
        }

        if (review == 5) {
            review1.setBackgroundResource(R.drawable.star_yellow)
            review2.setBackgroundResource(R.drawable.star_yellow)
            review3.setBackgroundResource(R.drawable.star_yellow)
            review4.setBackgroundResource(R.drawable.star_yellow)
            review5.setBackgroundResource(R.drawable.star_yellow)
        }
    }

}