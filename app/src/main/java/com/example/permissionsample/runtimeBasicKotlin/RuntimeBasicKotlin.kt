package com.example.permissionsample.runtimeBasicKotlin

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import com.example.permissionsample.PERMISSIONS
import com.example.permissionsample.databinding.ActivityRuntimeBasicKotlinBinding
import com.example.permissionsample.extensions.*

class RuntimeBasicKotlin : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var binding: ActivityRuntimeBasicKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRuntimeBasicKotlinBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnPreview.setOnClickListener { showCameraPreview() }
    }

    private fun showCameraPreview() {
        if (checkSelfPermissionCompat(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            binding.coordinator.showSnack("Camera Permission Available")
            goTo(CameraActivity::class.java)
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (shouldShowPermissionRationaleCompat(Manifest.permission.CAMERA)) {
            binding.coordinator.showSnack("The app requires Camera permission to preview!")
            requestPermissionsCompat(
                arrayOf(Manifest.permission.CAMERA), PERMISSIONS.CAMERA_REQUEST_CODE
            )
        } else {
            binding.coordinator.showSnack("Permission Denied!")
            requestPermissionsCompat(
                arrayOf(Manifest.permission.CAMERA), PERMISSIONS.CAMERA_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.coordinator.showSnack("Permission Granted!")
            goTo(CameraActivity::class.java)
        } else {
            binding.coordinator.showSnack("Permission Denied!")
        }
    }

}