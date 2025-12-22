package com.dreamview.app.util

import android.content.Context
import android.content.pm.PackageManager
import java.security.MessageDigest

object AppSigUtil {
    fun sha256(ctx: Context): String? = try {
        val info = ctx.packageManager.getPackageInfo(
            ctx.packageName,
            PackageManager.GET_SIGNING_CERTIFICATES
        )
        val sig = info.signingInfo.apkContentsSigners.firstOrNull() ?: return null
        val md = MessageDigest.getInstance("SHA-256")
        md.digest(sig.toByteArray()).joinToString("") { "%02x".format(it) }
    } catch (_: Throwable) { null }
}
