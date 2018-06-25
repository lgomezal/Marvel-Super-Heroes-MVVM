package com.costular.marvelheroes.presentation

import com.costular.marvelheroes.domain.model.MarvelHeroEntity

object FakeData {
    const val NAME = "Iron Man"
    const val REAL_NAME = "Anthony Edward \"Tony\" Stark"
    const val POWER = "Half man half robot"
    const val HEIGHT = "1.85m"
    const val ABILITIES = "Tony has a genius level intellect that allows him to invent a wide range of sophisticated devices, specializing in advanced weapons and armor. He possesses a keen business mind."
    val IRON_MAN = MarvelHeroEntity(NAME, "", REAL_NAME, HEIGHT, POWER, ABILITIES, arrayListOf())
}