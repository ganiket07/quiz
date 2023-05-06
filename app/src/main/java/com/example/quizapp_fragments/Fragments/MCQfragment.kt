package com.example.quizapp_fragments.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.MVC.QuizAppView
import com.example.quizapp_fragments.R

class MCQfragment:Fragment() {
    private lateinit var quizAppView: QuizAppView
//    private lateinit var MCQquestionsData:QuizAppModel
    private lateinit var questionMCQID:TextView
    private lateinit var opt1:RadioButton
    private lateinit var opt2:RadioButton
    private lateinit var opt3:RadioButton
    private lateinit var opt4:RadioButton
    private lateinit var optionsMCQ:RadioGroup
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizAppView= QuizAppView()
        val inf = inflater.inflate(R.layout.mcqfragments, container, false)
        val context=activity as QuizAppController
       val mcqQquestions=context.MCQquestionsData

        //Here the view is setting accordingly


       questionMCQID=inf.findViewById(R.id.questionformcq)
        opt1=inf.findViewById(R.id.option1)
        opt2=inf.findViewById(R.id.option2)
        opt3=inf.findViewById(R.id.option3)
        opt4=inf.findViewById(R.id.option4)
        optionsMCQ=inf.findViewById(R.id.options)
        return inf
    }
    fun getQuestionText(): TextView {
        return questionMCQID
    }
    fun getOptionsId(): List<RadioButton> {
        return listOf(opt1, opt2, opt3, opt4)
    }
    fun getOptionsforgrp(): RadioGroup {
        return optionsMCQ
    }

}