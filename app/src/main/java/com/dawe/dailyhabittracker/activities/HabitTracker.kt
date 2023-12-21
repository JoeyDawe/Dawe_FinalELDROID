package com.dawe.dailyhabittracker.activities

class HabitTracker {
    private val habits = mutableListOf<Habit>()
    private val history = mutableListOf<Habit>()

    fun addHabit(name: String, description: String, frequency: String, timing: String) {
        val habit = Habit(name, description, frequency, timing)
        habits.add(habit)
    }

    fun updateProgress(habitName: String, progress: Int) {
        val habit = habits.find { it.name == habitName }
        habit?.let {
            it.progress = progress
            it.streak++
            if (progress >= 100) {
                recordCompletedHabit(it)
            }
        }
    }

    fun getStreaks(): List<Pair<String, Int>> {
        return habits.map { habit -> Pair(habit.name, habit.streak) }
    }

    fun getHabitPerformance(): Float {
        if (habits.isEmpty()) return 0.0f

        val totalProgress = habits.sumOf { it.progress }
        return totalProgress.toFloat() / (habits.size * 100)
    }


    private fun recordCompletedHabit(habit: Habit) {
        history.add(habit.copy())
    }

}
