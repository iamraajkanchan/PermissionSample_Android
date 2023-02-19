package com.example.permissionsample.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}