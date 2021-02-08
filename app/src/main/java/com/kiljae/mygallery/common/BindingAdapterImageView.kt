package com.kiljae.mygallery.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import java.io.File

object BindingAdapterImageView {

    @BindingAdapter("loadStorageImage")
    @JvmStatic
    fun loadStorageImage(view : ImageView, url: String){
        view.load(File(url))
    }
}