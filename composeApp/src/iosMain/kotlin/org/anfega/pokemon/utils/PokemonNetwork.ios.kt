package org.anfega.pokemon.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import
import platform.SystemConfiguration.SCNetworkReachabilityGetFlags
import platform.SystemConfiguration.kSCNetworkFlagsReachable

actual class PokemonNetwork {
    @OptIn(ExperimentalForeignApi::class)
    actual fun isConnectedOrConnecting(): Boolean {
        val reachability = SCNetworkReachabilityCreateWithName(null, "www.google.com")

        memScoped {
            val flags = alloc<UIntVar>()
            val gotFlags = SCNetworkReachabilityGetFlags(reachability, flags.ptr)
            val kSCNetworkFlagsConnectionRequired = 0u

            return gotFlags && (flags.value and kSCNetworkFlagsReachable != 0u) &&
                    (flags.value and kSCNetworkFlagsConnectionRequired == 0u)
        }
    }
}