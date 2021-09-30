package com.example.pokeapi.ui.pokeinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapi.R
import kotlinx.android.synthetic.main.activity_pokeinfo.*

class PokeInfoActivity: AppCompatActivity() {

    lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        initUI()

    }

    private fun initUI() {

        val id = intent.extras?.get("id") as Int
        viewModel.getPokemonIfo(id)

        viewModel.pokemonInfo.observe(this, { pokemon->
            nameTextView.text = pokemon.name
            heightText.text = "Altura: ${pokemon.height/10.0}m"
            weightText.text = "Peso: ${pokemon.weight/10.0}"
            val shinyChance = (1..8192).random()
            if(shinyChance == 8192)
                Glide.with(this).load(pokemon.sprites.frontShiny).into(imageView)
            else
                Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        })
    }
}