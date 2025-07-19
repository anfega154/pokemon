package org.anfega.pokemon.utils

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresPermission
import org.anfega.pokemon.MainActivity

actual class PokemonUtils(private val context: Context) {

    private val vibrator =
        MainActivity.ContextHolder.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    actual fun isConnectedOrConnecting(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

    @RequiresPermission(Manifest.permission.VIBRATE)
    actual fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            vibrator.vibrate(100)
        }
    }
}