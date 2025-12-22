package com.dreamview.ott.collector

import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import com.dreamview.ott.model.DeviceInfo
import java.security.MessageDigest

object DeviceInfoCollector {

    fun collect(ctx: Context): DeviceInfo {
        val androidId = try {
            Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (_: Throwable) { null }

        val dm = ctx.resources.displayMetrics
        val screen = "${dm.widthPixels}x${dm.heightPixels}"

        val abiList = android.os.Build.SUPPORTED_ABIS?.toList() ?: emptyList()

        return DeviceInfo(
            androidId = androidId,
            brand = android.os.Build.BRAND ?: "",
            model = android.os.Build.MODEL ?: "",
            manufacturer = android.os.Build.MANUFACTURER ?: "",
            device = android.os.Build.DEVICE ?: "",
            product = android.os.Build.PRODUCT ?: "",
            sdkInt = android.os.Build.VERSION.SDK_INT,
            release = android.os.Build.VERSION.RELEASE ?: "",
            abiList = abiList,
            screen = screen,
            packageName = ctx.packageName,
            appSigSha256 = appSignatureSha256(ctx)
        )
    }

    private fun appSignatureSha256(ctx: Context): String? {
        return try {
            val info = ctx.packageManager.getPackageInfo(
                ctx.packageName,
                PackageManager.GET_SIGNING_CERTIFICATES
            )
            val sig = info.signingInfo.apkContentsSigners.firstOrNull() ?: return null
            val md = MessageDigest.getInstance("SHA-256")
            md.digest(sig.toByteArray()).joinToString("") { "%02x".format(it) }
        } catch (_: Throwable) { null }
    }
}
