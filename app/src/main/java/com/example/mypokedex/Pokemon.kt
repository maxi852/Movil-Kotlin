package com.example.mypokedex

data class Pokemon(
    val id: Int,
    val name: String,
    val vida: Int,
    val ataque: Int,
    val defensa: Int,
    val velocidad: Int,
    val tipo: PokemonType,
    val url: String
)
enum class PokemonType{
    AGUA,FUEGO, PLANTA, ELECTRICO,LUCHA
}