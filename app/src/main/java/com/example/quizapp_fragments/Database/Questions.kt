package com.example.quizapp_fragments.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class Questions(
    @PrimaryKey()
    val question: String,
    val answerIndex: Int

)