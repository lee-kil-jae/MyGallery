package com.kiljae.mygallery.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataGalleryFolder(var id: Int, var name: String, var url: String, var count: Int=0): Parcelable {
    constructor() : this(0, "", "", 0)
}