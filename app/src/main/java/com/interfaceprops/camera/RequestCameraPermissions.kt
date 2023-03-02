package com.interfaceprops.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestCameraPermissions(
    permission: String = android.Manifest.permission.CAMERA,
){
    val cameraPermissionState = rememberPermissionState(permission = permission)
    LaunchedEffect(true){
        cameraPermissionState.launchPermissionRequest()
    }
}