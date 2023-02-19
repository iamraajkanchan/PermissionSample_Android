package com.example.permissionsample.extensions

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

fun AppCompatActivity.checkSelfPermissionCompat(permission: String): Int {
    return ActivityCompat.checkSelfPermission(this, permission)
}

fun AppCompatActivity.requestPermissionsCompat(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}


fun AppCompatActivity.requestPermissionWithRationaleCompat(
    permission: String, requestCode: Int, execute: () -> Unit
) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        val provideRationale = shouldShowRequestPermissionRationale(permission)
        if (provideRationale) {
            execute.invoke()
        } else {
            requestPermissions(arrayOf(permission), requestCode)
        }
    }
}

fun AppCompatActivity.shouldShowPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun Activity.goTo(customClass: Class<*>) {
    Intent(this, customClass).apply {
        startActivity(this)
    }
}