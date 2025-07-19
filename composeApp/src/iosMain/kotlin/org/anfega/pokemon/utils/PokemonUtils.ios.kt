package org.anfega.pokemon.utils

import kotlinx.cinterop.ExperimentalForeignApi

//import platform.SystemConfiguration.SCNetworkReachabilityGetFlags
//import platform.SystemConfiguration.kSCNetworkFlagsReachable
//import platform.UIKit.UIImpactFeedbackGenerator
//import platform.UIKit.UIImpactFeedbackStyleLight

// Se deja la funcion que se debe usar para verificar la conectividad de red en iOS, aunque no se implementa aquí ya que no tengo un equipo MAC
actual class PokemonUtils {
    @OptIn(ExperimentalForeignApi::class)
    actual fun isConnectedOrConnecting(): Boolean {
//        val reachability = SCNetworkReachabilityCreateWithName(null, "www.google.com")
//
//        memScoped {
//            val flags = alloc<UIntVar>()
//            val gotFlags = SCNetworkReachabilityGetFlags(reachability, flags.ptr)
//            val kSCNetworkFlagsConnectionRequired = 0u
//
//            return gotFlags && (flags.value and kSCNetworkFlagsReachable != 0u) &&
//                    (flags.value and kSCNetworkFlagsConnectionRequired == 0u)
//        }
        return true // Placeholder for actual implementation
    }

    //    actual fun vibrate() {
//        UIImpactFeedbackGenerator(UIImpactFeedbackStyleLight).impactOccurred()
//    }
    actual fun vibrate() {
        // implemebntación comentada por que se esta trabajando en Windows lo que ha inpedido que se pueda probar
    }
}