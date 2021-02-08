package com.kiljae.mygallery.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mygallery.data.DataGalleryFolder
import com.kiljae.mygallery.data.DataGalleryImage
import com.kiljae.mygallery.model.AdapterGalleryFolder
import com.kiljae.mygallery.model.AdapterGalleryImageList
import com.kiljae.mygallery.viewmodel.GalleryFolderViewModel
import com.kiljae.mygallery.viewmodel.GalleryImageListViewModel
import kotlinx.android.synthetic.main.activity_gallery_folder.*

object BindingAdapterRecyclerView {

    @BindingAdapter(value = ["folderItems", "folderViewModel"], requireAll = true)
    @JvmStatic
    fun initFolderListView(view : RecyclerView, items : List<DataGalleryFolder>, vm : GalleryFolderViewModel){
        view.adapter?.run {
            if (this is AdapterGalleryFolder) {
                this.items = items
                this.vm = vm
            }
        }?: run{
            AdapterGalleryFolder(items, vm)
                .apply {
                    view.adapter = this
                }
        }

        (view.layoutManager as GridLayoutManager).setSpanSizeLookup(object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position){
                    0 -> 6
                    1,2 -> 3
                    else -> 2
                }
            }
        })
    }

    @BindingAdapter(value = ["imageListItems", "imageListViewModel"], requireAll = true)
    @JvmStatic
    fun initImageListView(view : RecyclerView, items : List<DataGalleryImage>, vm : GalleryImageListViewModel){
        view.adapter?.run {
            if (this is AdapterGalleryImageList) {
                this.items = items
                this.vm = vm
            }
        }?: run{
            AdapterGalleryImageList(items, vm)
                .apply {
                    view.adapter = this
                }
        }
    }
}