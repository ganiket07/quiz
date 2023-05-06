package com.example.quizapp_fragments.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/*
 It is used to access the Room database that stores multiple choice questions.
 */
@Dao
interface Questions_McqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMcqQuestions(questions: MutableList<QuestionsMCQ>)
    @Query("SELECT * FROM questionsMcq_table")
    suspend fun getAllMcqQuestions(): List<QuestionsMCQ>
}
