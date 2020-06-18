package com.harish.lowes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/* Created by harishrevuri on 6/17/20 */
@Parcelize
class ForecastModel(var temp: String, var feels_like: String, var cloud: String, var description: String) : Parcelable