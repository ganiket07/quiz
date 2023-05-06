package com.example.quizapp_fragments.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Questions::class], version = 1)
abstract class QuestionRoomData : RoomDatabase() {
    abstract fun questionsDao():QuestionsDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionRoomData? = null

        fun getDatabase(context: Context): QuestionRoomData {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionRoomData::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
