package com.dreamview.ott.util

import java.net.URI

object UrlUtil {

    /**
     * Accept:
     * - URL penuh: https://dreamview-ott.xxx.workers.dev/abc
     * - Host sahaja: dreamview-ott.xxx.workers.dev
     *
     * Return hostname sahaja (lowercase) atau null kalau tak valid.
     */
    fun extractHost(input: String): String? {
        val s = input.trim()
        if (s.isEmpty()) return null

        // If user enters host only, add https:// just to parse
        val candidate = if (s.contains("://")) s else "https://$s"

        return try {
            val uri = URI(candidate)
            val host = uri.host ?: return null
            host.lowercase()
        } catch (_: Throwable) {
            null
        }
    }

    fun normalizeBaseUrl(host: String): String {
        // kita pakai https sahaja untuk consistent
        return "https://${host.trim().lowercase()}"
    }
}
