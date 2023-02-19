package com.example.permissionsample.runtimeBasicKotlin

import android.content.Context
import android.hardware.Camera
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class CameraPreview @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defineStyleAttr: Int = 0,
    private val camera: Camera? = null,
    private val cameraInfo: Camera.CameraInfo? = null,
    private val displayOrientation: Int = 0,
    private val iCameraPreview: ICameraPreview
) : SurfaceView(context, attrs, defineStyleAttr), SurfaceHolder.Callback {

    init {
        if (camera != null && cameraInfo != null) {
            holder.addCallback(this@CameraPreview)
        }
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        try {
            camera?.run {
                setPreviewDisplay(surfaceHolder)
                startPreview()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        if (surfaceHolder.surface == null) {
            iCameraPreview.showMessage("Preview surface does not exist!!!")
            return
        }
        camera?.run {
            stopPreview()
            cameraInfo?.let {
                setDisplayOrientation(displayOrientation)
            }
            setPreviewDisplay(surfaceHolder)
            startPreview()
        }
        iCameraPreview.showMessage("Camera preview started.")
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {

    }
}