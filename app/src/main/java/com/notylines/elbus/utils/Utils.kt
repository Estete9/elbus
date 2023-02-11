package com.notylines.elbus.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import java.io.File


fun checkPermissions(
    context: Context,
    onPermissionUpdate: (Boolean) -> Unit = {},
    onPermissionRequest: () -> Unit = {}
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED

    ) {
        onPermissionUpdate(true)
    } else {
        onPermissionRequest()
    }

}


fun Context.hasLocationPermissions(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

fun File.writeBitmap(bmp: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bmp.compress(format, quality, out)
        out.flush()
    }
}
