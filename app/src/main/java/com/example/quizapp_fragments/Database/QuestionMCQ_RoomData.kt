package com.example.quizapp_fragments.Database

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [QuestionsMCQ::class], version = 2)
@TypeConverters(Converters::class)
abstract class QuestionMCQ_RoomData : RoomDatabase() {
    abstract fun questionsMCQ_Dao():Questions_McqDao
    // here we are creating the instance of the QuestionMCQ_RoomData using compainion Object so that it can't be asscessed outside
    companion object {
        @Volatile
        private var INSTANCE: QuestionMCQ_RoomData? = null

        fun getDatabaseMCQ(context: Context): QuestionMCQ_RoomData {


            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionMCQ_RoomData::class.java,
                    "app_database1"
                )
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
/*
These functions are used in Room database to convert data types between
 the format that is stored in the database and the format that is used by the app.
 */
class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}


