package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.MVC.QuizAppView
import com.example.quizapp_fragments.R

class TFfragment():Fragment() {
    lateinit var quizAppView: QuizAppView
    lateinit var question:TextView
    lateinit var trueoption:RadioButton
    lateinit var falseoption:RadioButton
    lateinit var optionsTF:RadioGroup
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizAppView= QuizAppView()
        val inf = inflater.inflate(R.layout.tf_fragments, container, false)
        val context = activity as QuizAppController
//        val tfQuestions=context.TFquestions

        //Here the view is setting accordingly

        question=inf.findViewById(R.id.questiontf)
        trueoption=inf.findViewById(R.id.trueOption)
        falseoption=inf.findViewById(R.id.falseOption)
        optionsTF=inf.findViewById(R.id.optionsfortf)

        return inf
    }
fun getQuestionText(): TextView {
    return question
}
    fun getOptionsTF(): List<RadioButton> {
        return listOf(trueoption,falseoption)
    }
    fun getOptionsforgrp(): RadioGroup {
        return optionsTF
    }
}

