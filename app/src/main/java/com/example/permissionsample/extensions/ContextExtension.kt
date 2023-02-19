package com.example.permissionsample.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

fun Context.hasPermission(permission: String): Boolean {
    /**
     * Background permissions didn't exit prior to Q, so it's approved by default.
     * */
    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION && Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
        return true
    }
    return ActivityCompat.checkSelfPermission(
        this, permission
    ) == PackageManager.PERMISSION_GRANTED
}