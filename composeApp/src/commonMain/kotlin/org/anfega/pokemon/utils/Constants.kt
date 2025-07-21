package org.anfega.pokemon.utils

sealed class Routes {
    companion object {
        const val HOME = "/home"
        const val CAR = "/car"
    }

}

object Constants {
    const val NO_CONNECTION_MESSAGE = "No internet connection. Please check your network settings."
}