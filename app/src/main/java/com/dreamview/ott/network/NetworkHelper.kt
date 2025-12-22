package com.dreamview.ott.network

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object NetworkHelper {
    private val client = OkHttpClient()
    private val JSON = "application/json; charset=utf-8".toMediaType()

    fun postJson(url: String, json: String): String {
        val body = json.toRequestBody(JSON)
        val req = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(req).execute().use { res ->
            val text = res.body?.string() ?: ""
            if (!res.isSuccessful) throw RuntimeException("HTTP ${res.code}: $text")
            return text
        }
    }
}
