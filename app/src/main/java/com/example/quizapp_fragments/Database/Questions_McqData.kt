package com.example.quizapp_fragments.Database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp_fragments.MVC.QuizAppModel
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import org.json.JSONObject

class Questions_McqData {
    var questionsForTFnew:MutableList<QuestionsMCQ>?=null

    fun dataQuestions_Mcq(context: Context,position:Int): MutableList<QuestionsMCQ>? = runBlocking{

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        val db = QuestionMCQ_RoomData.getDatabaseMCQ(context)


        val jsonString = context.assets.open("MCQques.json").bufferedReader().use { it.readText() }
        val quesJsonArray = JsonParser.parseString(jsonString).asJsonObject
        Log.d("quesJsonArray", quesJsonArray.toString())
        val keys = quesJsonArray.keySet().toTypedArray()[position!!]
        Log.d("quesJsonArray", keys.toString())
        Log.d("quesJsonArray", quesJsonArray[keys].toString())
        val questionsJson = quesJsonArray[keys.toString()].asJsonArray
        Log.d("questionsJson", questionsJson[0].toString())


        val questionsForMCQ = mutableListOf<QuestionsMCQ>()

        Log.d("MyFirstQuestion",questionsJson[1].asJsonObject["ques"].toString())

        for (i in 0 until questionsJson.size()) {
            val questionJson = questionsJson[i]
            val options = listOf(
                questionJson.asJsonObject["opt1"],
                questionJson.asJsonObject["opt2"],
                questionJson.asJsonObject["opt3"],
                questionJson.asJsonObject["opt4"]
            )
            val answerIndex = questionJson.asJsonObject["ans"]
            val question =
                QuestionsMCQ(questionJson.asJsonObject["ques"].toString(), options as List<String>, answerIndex.asInt)
            questionsForMCQ.add(question)
        }
        Log.d("ques1", questionsForMCQ.toString())

//        val job1= GlobalScope.launch{
//            db.questionsMCQ_Dao().insertMcqQuestions(questionsForMCQ)
//        }
//        job1.join()
//        val job2= GlobalScope.launch {
//            questionsForTFnew = db.questionsMCQ_Dao().getAllMcqQuestions() as MutableList<QuestionsMCQ>?
//        }
//        job2.join()

        return@runBlocking questionsForMCQ
    }

}