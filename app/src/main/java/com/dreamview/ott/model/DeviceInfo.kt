package com.dreamview.ott.model

data class DeviceInfo(
    val androidId: String?,
    val brand: String,
    val model: String,
    val manufacturer: String,
    val device: String,
    val product: String,
    val sdkInt: Int,
    val release: String,
    val abiList: List<String>,
    val screen: String,
    val packageName: String,
    val appSigSha256: String?
)
