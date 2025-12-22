package com.dreamview.ott.util

import com.dreamview.ott.model.DeviceInfo
import com.dreamview.ott.model.DrmInfo
import java.security.MessageDigest

object FingerprintUtil {

    private fun sha256(s: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return md.digest(s.toByteArray(Charsets.UTF_8)).joinToString("") { "%02x".format(it) }
    }

    fun make(d: DeviceInfo, drm: DrmInfo): String {
        val raw = listOf(
            d.androidId ?: "",
            d.brand, d.model, d.manufacturer,
            d.device, d.product,
            d.sdkInt.toString(), d.release,
            d.screen,
            d.abiList.joinToString(","),
            drm.widevineSupported.toString(),
            drm.securityLevel ?: "",
            drm.systemId ?: "",
            drm.vendor ?: "",
            drm.version ?: ""
        ).joinToString("|")

        return sha256(raw)
    }
}
