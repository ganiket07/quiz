package com.example.quizapp_fragments.MVC

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class QuizAppModel(context: Context) {
    data class TFQuizQuestion(
        val question: String,
        val answerIndex: Int
    )
     // Here,it is accessing the json file for the TFQuestions using json Parser.
    fun getQuestionsForTF(context: Context): List<TFQuizQuestion> {
        val jsonString =
            context.assets.open("TFquestions.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val questionsJson = json.getJSONArray("questions")
        val questionsForTF = mutableListOf<TFQuizQuestion>()

        for (i in 0 until questionsJson.length()) {
            val questionJson = questionsJson.getJSONObject(i)
            val answerIndex = questionJson.getInt("ans") - 1
            val question = TFQuizQuestion(questionJson.getString("ques"), answerIndex)
            questionsForTF.add(question)
        }

        return questionsForTF
    }


    data class MCQQuizQuestion(
        val question: String,
        val options: List<String>,
        val answerIndex: Int
    )

    // Here,it is accessing the json file for the MCQQuestions using json Parser.
    fun getQuestionsForMCQ(context: Context): List<MCQQuizQuestion> {
        val jsonString =
            context.assets.open("MCQQuestions.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val questionsJson = json.getJSONArray("questions")
        val questionsForMCQ = mutableListOf<MCQQuizQuestion>()

        for (i in 0 until questionsJson.length()) {
            val questionJson = questionsJson.getJSONObject(i)
            val options = listOf(
                questionJson.getString("opt1"),
                questionJson.getString("opt2"),
                questionJson.getString("opt3"),
                questionJson.getString("opt4")
            )
            val answerIndex = questionJson.getInt("ans") - 1
            val question = MCQQuizQuestion(questionJson.getString("ques"), options, answerIndex)
            questionsForMCQ.add(question)
        }

        return questionsForMCQ
    }
}
