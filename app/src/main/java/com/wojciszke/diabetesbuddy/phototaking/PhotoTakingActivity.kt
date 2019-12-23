package com.wojciszke.diabetesbuddy.phototaking

import android.Manifest
import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.BasePermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import com.wojciszke.diabetesbuddy.R

class PhotoTakingActivity : AppCompatActivity() {

    val cameraManager by lazy { getSystemService(Context.CAMERA_SERVICE) as CameraManager }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : BasePermissionListener() {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    setContentView(R.layout.activity_photo_taking)

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(this@PhotoTakingActivity, "Missing camera permission", Toast.LENGTH_SHORT).show()
                }
            }).check()


    }
}