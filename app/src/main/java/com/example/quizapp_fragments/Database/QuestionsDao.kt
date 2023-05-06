package com.example.quizapp_fragments.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: MutableList<Questions>)
    @Query("SELECT * FROM questions_table")
    suspend fun getAllQuestions(): List<Questions>
}
