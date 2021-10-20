package com.tobiasstrom.stairs.common.service

import android.app.Activity

interface ReviewService {
    fun requestReview(activity: Activity, onCompleteCallback: ((Boolean) -> Unit)?)
}
