package com.kiljae.mygallery.view

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.kiljae.mygallery.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedPermission.with(this)
            .setRationaleMessage(R.string.need_read_storage_permission)
            .setDeniedMessage(R.string.denied_read_storage_permission)
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    Toast.makeText(this@MainActivity, R.string.granted_read_storage_permission, Toast.LENGTH_SHORT).show()
                }

                override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                    Toast.makeText(this@MainActivity, R.string.denied_read_storage_permission, Toast.LENGTH_SHORT).show()
                }
            }).check()


        btnGoGallery.setOnClickListener {
            startActivity(Intent(this@MainActivity, GalleryFolderActivity::class.java))
        }
    }
}