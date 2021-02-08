package com.kiljae.mygallery.viewmodel

import android.content.ContentResolver
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import com.kiljae.mygallery.common.BaseViewModel
import com.kiljae.mygallery.common.NotNullMutableLiveData
import com.kiljae.mygallery.data.DataGalleryFolder
import com.kiljae.mygallery.data.DataGalleryImage

class GalleryImageListViewModel(val folder: DataGalleryFolder): BaseViewModel() {
    companion object{
        val TAG = "GalleryImageListViewModel"
        val INDEX_IMAGE_ID = 0
        val INDEX_IMAGE_URL = 1

    }

    val title: NotNullMutableLiveData<String> = NotNullMutableLiveData(folder.name + " (" + folder.count + ")")
    val items: NotNullMutableLiveData<MutableList<DataGalleryImage>> = NotNullMutableLiveData(mutableListOf())
    val clickItem : MutableLiveData<DataGalleryImage> = MutableLiveData()


    fun loadImageList(contentResolver: ContentResolver){
        val searchParams = MediaStore.MediaColumns.BUCKET_ID + " = " + folder.id

        val columns = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            columns,
            searchParams,
            null,
            "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        )

        cursor?.let {imageCursor ->
            while (imageCursor.moveToNext()) {
                val dataGalleryImage = DataGalleryImage(imageCursor.getInt(INDEX_IMAGE_ID), imageCursor.getString(INDEX_IMAGE_URL).toString())
                items.value.add(dataGalleryImage)
            }
            imageCursor.close()
        }
    }

    fun onClickImage(data: DataGalleryImage){
        clickItem.postValue(data)
    }
}