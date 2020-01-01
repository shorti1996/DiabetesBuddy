package com.wojciszke.diabetesbuddy.phototaking

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import com.wojciszke.diabetesbuddy.R
import kotlinx.android.synthetic.main.activity_photo_taking.*

class PhotoTakingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_taking)

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : BasePermissionListener() {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        startCameraPreview(camera_view)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Toast.makeText(this@PhotoTakingActivity, "Missing camera permission", Toast.LENGTH_SHORT).show()
                    }
                }).check()
    }
}