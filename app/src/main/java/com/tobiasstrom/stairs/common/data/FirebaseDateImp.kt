package com.tobiasstrom.stairs.common.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tobiasstrom.stairs.tracking.model.TrackedActivity
import kotlinx.coroutines.delay
import timber.log.Timber

class FirebaseDateImp : FirebaseData {

    private val db = Firebase.firestore
    override suspend fun saveActivity(activity: TrackedActivity) {
        db.collection(ACTIVITY_COLlECTION).add(activity)
    }

    override suspend fun getActivity(): List<TrackedActivity> {
        val list: MutableList<TrackedActivity> = mutableListOf()
        var length: Int = 100
        val data = db.collection(ACTIVITY_COLlECTION)
        data.get()
            .addOnSuccessListener { activitys ->
                length = activitys.size()
                for (activity in activitys) {
                    val item = activity.toObject<TrackedActivity>()
                    list.add(item)
                    Timber.d(item.toString())
                }
            }
            .addOnFailureListener { exeption ->
                Timber.d(exeption)
            }
        do {
            delay(100)
            Timber.d(list.toString())
        } while (list.size < length)


        return list
    }

    companion object {
        const val ACTIVITY_COLlECTION = "activity"
    }
}
