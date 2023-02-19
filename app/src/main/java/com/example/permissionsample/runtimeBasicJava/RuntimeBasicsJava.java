package com.example.permissionsample.runtimeBasicJava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.permissionsample.MainActivity;
import com.example.permissionsample.PERMISSIONS;
import com.example.permissionsample.databinding.ActivityRuntimeBasicsJavaBinding;
import com.example.permissionsample.runtimeBasicKotlin.CameraActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

class RuntimeBasicsJava extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private ActivityRuntimeBasicsJavaBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRuntimeBasicsJavaBinding.inflate(LayoutInflater.from(RuntimeBasicsJava.this));
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCameraPreview();
            }
        });
    }

    private void showCameraPreview() {
        if (ActivityCompat.checkSelfPermission(RuntimeBasicsJava.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            showSnacks("Permission Granted!");
            startCamera();
        } else {
            requestCameraPermission();
        }
    }

    private void startCamera() {
        Intent cameraIntent = new Intent(RuntimeBasicsJava.this, CameraActivity.class);
        startActivity(cameraIntent);
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(RuntimeBasicsJava.this, Manifest.permission.CAMERA)) {
            showSnacks("The camera permission is required to show its preview!");
        } else {
            showSnacks("Permission Denied!");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSIONS.CAMERA_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(RuntimeBasicsJava.this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS.CAMERA_REQUEST_CODE);
        }
    }

    private void showSnacks(String message) {
        Snackbar.make(binding.coordinator, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS.CAMERA_REQUEST_CODE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showSnacks("Permission Granted!");
                startCamera();
            } else {
                showSnacks("Permission Denied");
                Intent mainIntent = new Intent(RuntimeBasicsJava.this, MainActivity.class);
                startActivity(mainIntent);
            }
        }
    }
}