package com.tobiasstrom.stairs.common.data

import com.tobiasstrom.stairs.tracking.model.TrackedActivity

interface FirebaseData {
    suspend fun saveActivity(activity: TrackedActivity)

    suspend fun getActivity(): List<TrackedActivity>
}