package com.kiljae.mygallery.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataGalleryImage(var id: Int, var url: String): Parcelable {
    constructor() : this(0, "")
}