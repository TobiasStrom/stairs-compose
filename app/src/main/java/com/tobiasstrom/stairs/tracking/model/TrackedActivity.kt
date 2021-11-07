package com.tobiasstrom.stairs.tracking.model

data class TrackedActivity(
    val buildingName: String = "Rebel",
    val startFloor: Int = 1,
    val endFloor: Int = 11,
    val hastFloorZero: Boolean = false,
    val stepsEachFloor: Int = 8,
    val heightStep: Int = 17
)