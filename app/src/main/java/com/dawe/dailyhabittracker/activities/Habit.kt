package com.dawe.dailyhabittracker.activities

data class Habit(
    val name: String,
    val description: String,
    var frequency: String,
    var timing: String,
    var progress: Int = 0,
    var streak: Int = 0
)