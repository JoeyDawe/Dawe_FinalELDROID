package com.dawe.dailyhabittracker.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    private lateinit var habitNameEditText: EditText
    private lateinit var habitDescriptionEditText: EditText
    private lateinit var frequencyEditText: EditText
    private lateinit var timingEditText: EditText
    private lateinit var progressEditText: EditText
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var streakTextView: TextView
    private lateinit var performanceTextView: TextView

    private val habitTracker = HabitTracker()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        habitNameEditText = findViewById(R.id.habitNameEditText)
        habitDescriptionEditText = findViewById(R.id.habitDescriptionEditText)
        frequencyEditText = findViewById(R.id.frequencyEditText)
        timingEditText = findViewById(R.id.timingEditText)
        progressEditText = findViewById(R.id.progressEditText)
        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        streakTextView = findViewById(R.id.streakTextView)
        performanceTextView = findViewById(R.id.performanceTextView)

        addButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            val habitDescription = habitDescriptionEditText.text.toString()
            val frequency = frequencyEditText.text.toString()
            val timing = timingEditText.text.toString()
            habitTracker.addHabit(habitName, habitDescription, frequency, timing)
            updateStreaks()
        }

        updateButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            val progress = progressEditText.text.toString().toIntOrNull() ?: 0
            habitTracker.updateProgress(habitName, progress)
            updateStreaks()
            updatePerformance()

        }

    }

    private fun updateStreaks() {
        val streaks = habitTracker.getStreaks()
        val streaksText = streaks.joinToString("\n") { "${it.first}: ${it.second}" }
        val formattedText = getString(R.string.habit_streaks_template, streaksText)
        streakTextView.text = formattedText
    }

    private fun updatePerformance() {
        val performance = habitTracker.getHabitPerformance() * 100
        val formattedText = getString(R.string.performance_template, performance)
        performanceTextView.text = formattedText
    }

}

