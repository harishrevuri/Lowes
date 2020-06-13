package com.harish.lowes.utils

import android.content.Context
import android.net.ConnectivityManager

object MyUtils {
    const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}