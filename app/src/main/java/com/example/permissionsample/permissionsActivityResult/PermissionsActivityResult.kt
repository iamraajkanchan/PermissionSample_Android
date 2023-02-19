package com.example.permissionsample.permissionsActivityResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.permissionsample.databinding.ActivityPermissionsResultBinding

internal class PermissionsActivityResult : AppCompatActivity() {
    private lateinit var binding: ActivityPermissionsResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityPermissionsResultBinding.inflate(LayoutInflater.from(this@PermissionsActivityResult))
        setContentView(binding.root)
    }
}