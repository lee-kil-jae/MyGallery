package com.kiljae.mygallery.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.kiljae.mygallery.R
import com.kiljae.mygallery.common.BaseActivity
import com.kiljae.mygallery.databinding.ActivityGalleryFolderBinding
import kotlinx.android.synthetic.main.activity_gallery_folder.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GalleryFolderActivity: BaseActivity<ActivityGalleryFolderBinding>() {
    companion object{
        val TAG = "GalleryFolderActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_gallery_folder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmFolder = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmFolder)

        viewDataBinding.vmFolder?.loadFolderInfo(contentResolver)

        viewDataBinding.vmFolder?.clickItem?.observe(this, Observer {
            val intent = Intent(this@GalleryFolderActivity, GalleryImageListActivity::class.java)
            intent.putExtra(GalleryImageListActivity.EXTRA_FOLDER, it)
            startActivity(intent)
        })

//        (rcvFolders.layoutManager as GridLayoutManager).setSpanSizeLookup(object: SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return when(position){
//                    0 -> 6
//                    1,2 -> 3
//                    else -> 2
//                }
//            }
//        })

    }
}