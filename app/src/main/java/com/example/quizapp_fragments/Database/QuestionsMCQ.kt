package com.example.quizapp_fragments.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionsMcq_table")
data class QuestionsMCQ(
    @PrimaryKey()
    val question: String,
    val options: List<String>,
    val answerIndex: Int
    )