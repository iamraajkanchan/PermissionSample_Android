package com.example.permissionsample;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater
import com.example.permissionsample.dataAccessAuditing.DataAccessAuditing
import com.example.permissionsample.databinding.ActivityMainBinding;
import com.example.permissionsample.extensions.goTo
import com.example.permissionsample.permissionsActivityResult.PermissionsActivityResult
import com.example.permissionsample.runtimeBasicKotlin.RuntimeBasicKotlin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.tvDataAccessLabel.setOnClickListener { goTo(DataAccessAuditing::class.java) }
        binding.tvPermissionsActivity.setOnClickListener { goTo(PermissionsActivityResult::class.java) }
        binding.tvRuntimeBasicsJava.setOnClickListener {

        }
        binding.tvRuntimeBasicsKotlin.setOnClickListener { goTo(RuntimeBasicKotlin::class.java) }
    }
}