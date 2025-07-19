package org.anfega.pokemon.exception

class PokemonException(message: String?, cause: Throwable? = null) : Exception(message, cause) {
    init {
//        Firebase.crashlytics.recordException(this) Pensar en futura implementación
    }
}