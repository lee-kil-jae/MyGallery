package com.kiljae.mygallery.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mygallery.R
import com.kiljae.mygallery.data.DataGalleryImage
import com.kiljae.mygallery.databinding.RowGalleryImageListBinding
import com.kiljae.mygallery.viewmodel.GalleryImageListViewModel

class AdapterGalleryImageList(var items: List<DataGalleryImage> = arrayListOf(), var vm: GalleryImageListViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{

    }

    class ViewHolder(val binding: RowGalleryImageListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.row_gallery_image_list,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).binding.run {
            data = items[position]
            vmImageList = vm
        }
    }

}