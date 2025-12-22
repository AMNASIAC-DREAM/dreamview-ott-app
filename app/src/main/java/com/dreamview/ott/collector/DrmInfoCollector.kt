package com.dreamview.ott.collector

import android.media.MediaDrm
import com.dreamview.ott.model.DrmInfo
import java.util.UUID

object DrmInfoCollector {

    // Widevine UUID
    private val WIDEVINE_UUID = UUID(
        0xEDEF8BA979D64ACEL, 0xA3C827DCD51D21EDL
    )

    fun collectWidevine(): DrmInfo {
        val supported = try {
            MediaDrm.isCryptoSchemeSupported(WIDEVINE_UUID)
        } catch (_: Throwable) { false }

        if (!supported) return DrmInfo(false, null, null, null, null, null)

        var md: MediaDrm? = null
        return try {
            md = MediaDrm(WIDEVINE_UUID)

            fun prop(k: String): String? = try { md.getPropertyString(k) } catch (_: Throwable) { null }

            DrmInfo(
                widevineSupported = true,
                securityLevel = prop("securityLevel"), // L1 / L3
                systemId = prop("systemId"),
                vendor = prop("vendor"),
                version = prop("version"),
                description = prop("description")
            )
        } catch (_: Throwable) {
            DrmInfo(true, null, null, null, null, null)
        } finally {
            try { md?.release() } catch (_: Throwable) {}
        }
    }
}
