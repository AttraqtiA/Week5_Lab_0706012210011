package com.example.week5lab_0706012210011.model

data class IPKUIState(
    var total_SKS: Int = 0,
    var total_IPK: Double = 0.00,
    val CourseList: MutableList<Course> = mutableListOf()
)