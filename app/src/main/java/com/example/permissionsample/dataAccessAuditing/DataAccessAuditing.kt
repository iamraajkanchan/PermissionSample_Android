package com.example.permissionsample.dataAccessAuditing

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import com.example.permissionsample.databinding.ActivityDataAccessAuditingBinding
import com.example.permissionsample.PERMISSIONS
import com.example.permissionsample.extensions.hasPermission
import com.example.permissionsample.extensions.requestPermissionWithRationaleCompat
import com.example.permissionsample.extensions.showSnack

class DataAccessAuditing : AppCompatActivity() {

    private lateinit var binding: ActivityDataAccessAuditingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataAccessAuditingBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        if (hasPermission(Manifest.permission.CAMERA)) {
            binding.coordinator.showSnack("Permission Granted!!!")
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), PERMISSIONS.CAMERA_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS.CAMERA_REQUEST_CODE) {
            for (permission in permissions) {
                if (permission == Manifest.permission.CAMERA) {
                    for (grantResult in grantResults) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            binding.coordinator.showSnack("Permission Granted From Result!!!")
                        } else {
                            binding.coordinator.showSnack("Permission Denied From Result!!!")
                        }
                    }
                }
            }
        } else {
            requestPermissionWithRationaleCompat(
                Manifest.permission.CAMERA, PERMISSIONS.CAMERA_REQUEST_CODE
            ) { binding.coordinator.showSnack("Requesting Permission for Camera!!!") }
        }
    }
}