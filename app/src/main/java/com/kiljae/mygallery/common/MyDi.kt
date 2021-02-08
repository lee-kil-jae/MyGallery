package com.kiljae.mygallery.common

import com.kiljae.mygallery.data.DataGalleryFolder
import com.kiljae.mygallery.viewmodel.GalleryFolderViewModel
import com.kiljae.mygallery.viewmodel.GalleryImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelPart = module {
    viewModel {
        GalleryFolderViewModel()
    }

    viewModel {
        (folder: DataGalleryFolder) -> GalleryImageListViewModel(folder)
    }
}

var myDiModule = listOf(
    viewModelPart
)