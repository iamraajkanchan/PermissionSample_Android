package com.example.permissionsample.runtimeBasicKotlin

import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewbinding.ViewBinding
import com.example.permissionsample.databinding.LayoutCameraAvailableBinding
import com.example.permissionsample.databinding.LayoutCameraUnavailableBinding
import com.example.permissionsample.extensions.showSnack

private const val CAMERA_ID: Int = 0

class CameraActivity : AppCompatActivity(), ICameraPreview {

    private lateinit var binding: ViewBinding
    private lateinit var coordinator: CoordinatorLayout
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        camera = getCameraInstance(CAMERA_ID)
        val cameraInfo = Camera.CameraInfo()
        Camera.getCameraInfo(CAMERA_ID, cameraInfo)
        if (camera == null) {
            binding = LayoutCameraUnavailableBinding.inflate(LayoutInflater.from(this))
            setContentView(binding.root)
            coordinator = (binding as LayoutCameraUnavailableBinding).coordinator
        } else {
            binding = LayoutCameraAvailableBinding.inflate(LayoutInflater.from(this))
            setContentView(binding.root)
            coordinator = (binding as LayoutCameraAvailableBinding).coordinator
            val displayRotation = windowManager.defaultDisplay.rotation
            val cameraPreview =
                CameraPreview(this, null, 0, camera, cameraInfo, displayRotation, this)
            (binding as LayoutCameraAvailableBinding).frameLayout.addView(cameraPreview)
        }
    }

    override fun onPause() {
        super.onPause()
        releaseCamera()
    }

    override fun showMessage(message: String) {
        coordinator.showSnack(message)
    }

    private fun getCameraInstance(cameraId: Int): Camera? {
        try {
            camera = Camera.open(cameraId)
        } catch (e: Exception) {
            e.printStackTrace()
            coordinator.showSnack("Camera of $cameraId is not available")
        }
        return camera
    }

    private fun releaseCamera() {
        camera?.release()
        camera = null
    }
}