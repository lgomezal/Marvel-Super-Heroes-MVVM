package com.costular.marvelheroes.presentation.heroedetail

import android.content.Context
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
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.item_hero.view.*
import javax.annotation.meta.When

class MarvelHeroeDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let { fillHeroData(it) }

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
        heroeObservations.text = SpannableStringBuilder(hero.reviewText)

        review1.setOnClickListener {
            hero.review = 1
            hero.reviewText = heroeObservations.text.toString()
            putStartsReview(hero.review)
        }

        review2.setOnClickListener {
            hero.review = 2
            hero.reviewText = heroeObservations.text.toString()
            putStartsReview(hero.review)
        }

        review3.setOnClickListener {
            hero.review = 3
            hero.reviewText = heroeObservations.text.toString()
            putStartsReview(hero.review)
        }

        review4.setOnClickListener {
            hero.review = 4
            hero.reviewText = heroeObservations.text.toString()
            putStartsReview(hero.review)
        }

        review5.setOnClickListener {
            hero.review = 5
            hero.reviewText = heroeObservations.text.toString()
            putStartsReview(hero.review)
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

    fun putStartsReview(review: Int) {
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