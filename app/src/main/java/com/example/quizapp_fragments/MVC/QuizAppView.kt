package com.example.quizapp_fragments.MVC

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.quizapp_fragments.Database.Questions
import com.example.quizapp_fragments.Database.QuestionsMCQ
import com.example.quizapp_fragments.Database.demoFragment
import com.example.quizapp_fragments.Fragments.HistoryFragment
import com.example.quizapp_fragments.Fragments.LoginFragment
import com.example.quizapp_fragments.Fragments.NavigationQuestionFragment
import com.example.quizapp_fragments.R
import com.google.android.material.color.utilities.Score
import com.google.gson.Gson
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import kotlin.math.log

class QuizAppView {
    var counter=0
    var score=0
    var wrongans=0
    private lateinit var prevButton: Button
    private lateinit var neXtButton: Button
    var clicked=""

    //Here Handlers are coded

    @SuppressLint("SuspiciousIndentation")
    fun onClickFunctionalityTF(context: Context, view: View?, questions:List<Questions>, question:TextView?,
                               optsforTF:List<RadioButton>, optsforTFgrp:RadioGroup){
        neXtButton= view!!.findViewById(R.id.nextButton)
        prevButton=view.findViewById(R.id.prevButton)
        question!!.text=questions[counter].question
        clicked="Clicked"
        fun showQuestion() {
            val currentQuestion = questions[counter]
            question!!.text = currentQuestion.question
        }


        /*Here,it is  checking the condition and doing the part of navigation button
        and the value of scores and wrongAns for TFQuestions.
         */
    prevButton.setOnClickListener {
            counter--
            score--
            if (counter > -1 ) {
                showQuestion()
            }

        }
        question!!.text=questions[counter].question


        neXtButton.setOnClickListener {
            val selectedAnswerIndex = getSelectedAnswerIndexforTF(optsforTF)
            if (selectedAnswerIndex == questions[counter].answerIndex - 1) {
                score++
            }
            if (selectedAnswerIndex != questions[counter].answerIndex - 1){
                wrongans++
            }
            counter++
            optsforTFgrp.clearCheck()
            if (counter < questions.size) {
                showQuestion()
            }
            else {
                val args = Bundle()
                args.putInt("key", score)
                val fragmentManager =(context as FragmentActivity).supportFragmentManager
                val demoFragment=demoFragment()
                val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.flcontainer1,demoFragment)
                    fragmentTransaction.addToBackStack(null).commit()
                demoFragment.arguments = args

                val navigateQuesFrag = fragmentManager.findFragmentById(R.id.flcontainer2)
                if (navigateQuesFrag is NavigationQuestionFragment) {
                    fragmentTransaction.hide(navigateQuesFrag)
                }

                savingData(context)
            }

        }

    }



    fun onClickFunctionalityMCQ(
        context: Context,
        view: View?,
        questions: List<QuestionsMCQ>,
        questionforMcq:TextView?,
        opts:List<RadioButton>,
        optsforMCQgrp:RadioGroup

    ){
        neXtButton= view!!.findViewById(R.id.nextButton)
        prevButton=view.findViewById(R.id.prevButton)
        fun showQuestion() {
            val currentQuestion = questions[counter]
            Log.d("MyQuestionsToSet",currentQuestion.toString())
            Log.d("MyfirstQuestion",questions.toString())
            questionforMcq!!.text = currentQuestion.question

            val options = JsonPrimitive(currentQuestion.options.toString())
            val optionsArray = options.asString.split(",").toTypedArray()
            opts[0].text = optionsArray.elementAt(0).replace("\"","").replace("[","")
            opts[1].text = optionsArray.elementAt(1).replace("\"","")
            opts[2].text = optionsArray.elementAt(2).replace("\"","")
            opts[3].text = optionsArray.elementAt(3).replace("\"","").replace("]","")
        }
        showQuestion()

        /*Here,it is  checking the condition and doing the part of navigation button
      and the value of scores and wrongAns for MCQQuestions.
        */
        prevButton.setOnClickListener {
            Log.d(clicked, "True Button Clicked")
            counter--

            if (counter > -1) {
                showQuestion()
            }
        }

        neXtButton.setOnClickListener {
            Log.d(clicked, "True Button Clicked")

            val selectedAnswerIndex = getSelectedAnswerIndex(opts)
            if ((selectedAnswerIndex  ) == (questions[counter].answerIndex -1)) {
                score++
            }
            if (selectedAnswerIndex != questions[counter].answerIndex - 1){
                wrongans++
            }
            optsforMCQgrp.clearCheck()
            counter++
            if (counter < questions.size) {
                showQuestion()
            }
            else {

                val args = Bundle()
                args.putInt("key", score)
                val fragmentManager =(context as FragmentActivity).supportFragmentManager
                val demoFragment=demoFragment()
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flcontainer1,demoFragment)
                fragmentTransaction.addToBackStack(null).commit()
                demoFragment.arguments = args

                val navigateQuesFrag = fragmentManager.findFragmentById(R.id.flcontainer2)
                if (navigateQuesFrag is NavigationQuestionFragment) {
                    fragmentTransaction.hide(navigateQuesFrag)
                }


                savingData(context)
            }
        }

    }


    private fun getSelectedAnswerIndex(opts:List<RadioButton>): Int {
        return when {
            opts[0].isChecked -> 0
            opts[1].isChecked -> 1
            opts[2].isChecked -> 2
            opts[3].isChecked -> 3
            else -> -1 // no option selected
        }

    }
    private fun getSelectedAnswerIndexforTF(optsforTF:List<RadioButton>): Int {
        return when {
            optsforTF[0].isChecked -> 0
            optsforTF[1].isChecked -> 1
            else -> -1 // no option selected
        }

    }


    //Using shared Preferences the values of scores and Wrong Answer is getting saved
    fun savingData(context:Context){
        val sharedPreferences = context.getSharedPreferences("quiz-result", Context.MODE_PRIVATE)
        var noOfAttempts = sharedPreferences.getInt("noOfAttempts", 0)
        noOfAttempts++

//        var attemptsNo = sharedPreferences.getInt("AttemptsNo_$noOfAttempts", 0)
//        attemptsNo++
       var attemptsNo=noOfAttempts

        val editor = sharedPreferences.edit()
        editor.putInt("noOfAttempts", noOfAttempts)
        editor.putString("AttemptsNo_$noOfAttempts",Gson().toJson(attemptsNo))
        editor.putString("attempt_$noOfAttempts", Gson().toJson(score))
        editor.putString("Wrong attempt_$noOfAttempts", Gson().toJson(wrongans))
//        editor.clear()
        editor.apply()


    }

    
}