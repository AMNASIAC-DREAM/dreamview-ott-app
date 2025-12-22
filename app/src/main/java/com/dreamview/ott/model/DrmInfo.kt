package com.dreamview.ott.model

data class DrmInfo(
    val widevineSupported: Boolean,
    val securityLevel: String?,
    val systemId: String?,
    val vendor: String?,
    val version: String?,
    val description: String?
)
