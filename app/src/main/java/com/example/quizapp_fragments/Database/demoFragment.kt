package com.example.quizapp_fragments.Database

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quizapp_fragments.R
import com.google.android.material.color.utilities.Score

class demoFragment:Fragment() {
     var questions: MutableList<Questions>? = null
    var questionsMcq:MutableList<QuestionsMCQ>?=null
    private lateinit var viewScore: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf =inflater.inflate(R.layout.demofragment,container,false)

        val score=arguments?.getInt("key")
        viewScore=inf.findViewById(R.id.viewScore)

        viewScore.text= score.toString()


        return inf
    }
}