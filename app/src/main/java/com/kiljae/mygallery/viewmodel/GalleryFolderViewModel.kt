package com.kiljae.mygallery.viewmodel

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import com.kiljae.mygallery.common.BaseViewModel
import com.kiljae.mygallery.common.NotNullMutableLiveData
import com.kiljae.mygallery.data.DataGalleryFolder

class GalleryFolderViewModel : BaseViewModel(){
    companion object{
        val TAG = "GalleryFolderViewModel"
        val INDEX_BUCKET_ID = 0
        val INDEX_BUCKET_NAME = 1
        val INDEX_BUCKET_URL = 2
    }

    val items: NotNullMutableLiveData<MutableList<DataGalleryFolder>> = NotNullMutableLiveData(mutableListOf())
    val clickItem : MutableLiveData<DataGalleryFolder> = MutableLiveData()

    fun loadFolderInfo(contentResolver: ContentResolver){
        val PROJECTION_FOLDER = arrayOf(
            MediaStore.Images.ImageColumns.BUCKET_ID,
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Images.ImageColumns.DATA
        )
        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            PROJECTION_FOLDER,
            null,
            null,
            "$orderBy DESC"
        )

        cursor?.let { folderCursor ->
            var ids = mutableListOf<Int>()
            while (folderCursor.moveToNext()) {
                val folder = DataGalleryFolder(
                    folderCursor.getInt(INDEX_BUCKET_ID),
                    folderCursor.getString(INDEX_BUCKET_NAME),
                    folderCursor.getString(INDEX_BUCKET_URL)
                )
                if (!ids.contains(folder.id)) {
                    ids.add(folder.id)
                    items.value.add(folder)
                }
            }

            ids.clear()
            folderCursor.close()
        }

        for (folder in items.value) {
            val searchPrarams = MediaStore.MediaColumns.BUCKET_ID + " = " + folder.id
            val columns = arrayOf(MediaStore.Images.Media._ID)
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                searchPrarams,
                null,
                null
            )
            folder.count = cursor?.count?:0
            cursor?.close()
        }
    }

    fun onClickFolder(data: DataGalleryFolder){
        clickItem.postValue(data)
    }
}