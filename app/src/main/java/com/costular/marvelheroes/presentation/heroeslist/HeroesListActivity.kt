package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.costular.marvelheroes.R
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.di.components.ApplicationComponent
import com.costular.marvelheroes.di.components.DaggerGetMarvelHeroesListComponent
import com.costular.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.MainApp
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroeDetailViewModel
import com.costular.marvelheroes.presentation.util.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HeroesListActivity : AppCompatActivity() {

    private var heroImage : View? = null

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var heroesListViewModel: HeroesListViewModel

    lateinit var adapter: HeroesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecycler()
        setUpViewModel()
    }

    fun inject() {
        DaggerGetMarvelHeroesListComponent
                .builder()
                .applicationComponent((application as MainApp).component)
                .getMarvelHeroesListModule(GetMarvelHeroesListModule(this))
                .build()
                .inject(this)
    }

    private fun setUpViewModel() {
        bindEvents()
        heroesListViewModel.loadMarvelHeroes()
    }

    private fun setUpRecycler() {
        adapter = HeroesListAdapter ({ hero, image -> goToHeroDetail(hero, image) },
                                     { hero -> updateHero(hero) })
        heroesListRecycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        heroesListRecycler.itemAnimator = DefaultItemAnimator()
        heroesListRecycler.adapter = adapter
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(this, hero, image)
    }

    fun updateHero(hero: MarvelHeroEntity) {
        heroesListViewModel.updateHero(hero)
    }

    private fun bindEvents() {
        heroesListViewModel.isLoagingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })

        heroesListViewModel.heroesListState.observe(this, Observer {heroesList ->
            heroesList?.let {
                showHeroesList(it)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        heroesListLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun showHeroesList(heroes: List<MarvelHeroEntity>) {
        adapter.swapData(heroes)
    }

    private fun updateHeroDetail(hero: MarvelHeroEntity) {
        adapter.swapDataDetail(hero)
    }

    override fun onResume() {
        super.onResume()
        heroesListViewModel.loadMarvelHeroes()
    }

}
