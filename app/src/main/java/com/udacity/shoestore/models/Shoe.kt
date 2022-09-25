package com.udacity.shoestore.models

import android.os.Parcelable
import com.udacity.shoestore.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String = "Shoe",
    var size: Int = 16,
    var company: String = "Nike",
    var description: String = "No description",
    var image: Int = R.drawable.nike
) : Parcelable

