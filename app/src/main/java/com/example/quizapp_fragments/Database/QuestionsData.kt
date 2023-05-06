package com.example.quizapp_fragments.Database

import android.content.Context
import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import org.json.JSONObject

class QuestionsData {
     var questionsForTFnew:MutableList<Questions>?=null

      fun dataQuestions(context: Context,position:Int): MutableList<Questions>? = runBlocking{

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        val db = QuestionRoomData.getDatabase(context)

          Log.d("Positon2",position.toString())
          val jsonString = context.assets.open("TFQues.json").bufferedReader().use { it.readText() }
          val quesJsonArray = JsonParser.parseString(jsonString).asJsonObject
          Log.d("quesJsonArray", quesJsonArray.toString())
          val keys = quesJsonArray.keySet().toTypedArray()[position!!]
          Log.d("quesJsonArray", keys.toString())
          Log.d("quesJsonArray", quesJsonArray[keys].toString())
          val questionsJson = quesJsonArray[keys.toString()].asJsonArray
          Log.d("questionsJson", questionsJson[0].toString())

          Log.d("Positon2",position.toString())

          Log.d("Questions",questionsJson.toString())

         var questionsForTF = mutableListOf<Questions>()
         var questionsForTFnew = mutableListOf<Questions>()
        Log.d("QuestionsTF",questionsForTFnew.toString())

      Log.d("MyFirstQuestion",questionsJson[1].asJsonObject["ques"].toString())
         for (i in 0 until questionsJson.size()) {
             val questionJson = questionsJson[i]
             val answerIndex = questionJson.asJsonObject["ans"]
             val question = Questions(questionJson.asJsonObject["ques"].toString(), answerIndex.asInt)
             questionsForTF.add(question)
         }
        Log.d("ques1", questionsForTF.toString())

//          val job1= GlobalScope.launch{
//              db.questionsDao().insertQuestions(questionsForTF)
//          }
//          job1.join()
//          val job2=GlobalScope.launch {
//              questionsForTFnew = (db.questionsDao().getAllQuestions() as MutableList<Questions>?)!!
//          }
//          job2.join()

          return@runBlocking questionsForTF
      }

}
