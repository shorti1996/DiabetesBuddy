package com.wojciszke.diabetesbuddy.phototaking

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.util.Rational
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.lifecycle.LifecycleOwner

@SuppressLint("RestrictedApi")
fun LifecycleOwner.startCameraPreview(textureView: TextureView) {
    val previewConfig = PreviewConfig.Builder().apply {
        setTargetAspectRatioCustom(Rational(textureView.width, textureView.height))
    }.build()

    val preview = Preview(previewConfig)

    preview.setOnPreviewOutputUpdateListener {
        // To update the SurfaceTexture, we have to remove it and re-add it
        val parent = textureView.parent as ViewGroup
        parent.removeView(textureView)
        parent.addView(textureView, 0)

        textureView.surfaceTexture = it.surfaceTexture
        updateTransform(textureView)
    }
    CameraX.bindToLifecycle(this, preview)
}

private fun updateTransform(textureView: TextureView) {
    val matrix = Matrix()

    val centerX = textureView.width / 2f
    val centerY = textureView.height / 2f

    // Correct preview output to account for display rotation
    val rotationDegrees = when (textureView.display.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> return
    }
    matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

    textureView.setTransform(matrix)
}