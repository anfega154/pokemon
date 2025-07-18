package org.anfega.pokemon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform