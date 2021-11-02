package com.tobiasstrom.stairs.common.service

interface RemoteConfigService {
    suspend fun init(): RemoteConfigService
    fun isVersionLocked(): Boolean
    fun isChristmas(): Boolean
}
