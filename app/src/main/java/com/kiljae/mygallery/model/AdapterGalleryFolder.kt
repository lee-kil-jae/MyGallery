package com.kiljae.mygallery.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mygallery.R
import com.kiljae.mygallery.data.DataGalleryFolder
import com.kiljae.mygallery.databinding.RowGalleryFolderLargeBinding
import com.kiljae.mygallery.databinding.RowGalleryFolderNormalBinding
import com.kiljae.mygallery.databinding.RowGalleryFolderSmallBinding
import com.kiljae.mygallery.viewmodel.GalleryFolderViewModel

class AdapterGalleryFolder(var items: List<DataGalleryFolder> = arrayListOf(), var vm: GalleryFolderViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        val LARGE = 0
        val NORMAL = 1
        val SMALL = 2
    }

    class LargeViewHolder(val binding: RowGalleryFolderLargeBinding) : RecyclerView.ViewHolder(binding.root)
    class NormalViewHolder(val binding: RowGalleryFolderNormalBinding) : RecyclerView.ViewHolder(binding.root)
    class SmallViewHolder(val binding: RowGalleryFolderSmallBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> LARGE
            1,2 -> NORMAL
            else -> SMALL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            LARGE -> LargeViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.row_gallery_folder_large,
                    parent,
                    false
                )
            )
            NORMAL -> NormalViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.row_gallery_folder_normal,
                    parent,
                    false
                )
            )
            else -> SmallViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.row_gallery_folder_small,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = items.size



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == LARGE){
            (holder as LargeViewHolder).binding.run {
                data = items[position]
                vmFolder = vm
            }
        }else if(getItemViewType(position) == NORMAL){
            (holder as NormalViewHolder).binding.run {
                data = items[position]
                vmFolder = vm
            }
        }else if(getItemViewType(position) == SMALL){
            (holder as SmallViewHolder).binding.run {
                data = items[position]
                vmFolder = vm
            }
        }
    }

}