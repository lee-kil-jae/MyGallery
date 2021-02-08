package com.kiljae.mygallery.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.kiljae.mygallery.R
import com.kiljae.mygallery.common.BaseActivity
import com.kiljae.mygallery.databinding.ActivityGalleryImageListBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class GalleryImageListActivity: BaseActivity<ActivityGalleryImageListBinding>() {
    companion object{
        val TAG = "GalleryImageListActivity"
        val EXTRA_FOLDER = "EXTRA_FOLDER"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_gallery_image_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmImageList = getViewModel{ parametersOf(intent.getParcelableExtra(EXTRA_FOLDER)) }
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmImageList)

        viewDataBinding.vmImageList?.loadImageList(contentResolver)

        viewDataBinding.vmImageList?.clickItem?.observe(this, Observer {

        })

    }
}