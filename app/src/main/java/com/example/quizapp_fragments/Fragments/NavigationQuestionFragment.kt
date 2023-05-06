package com.example.quizapp_fragments.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quizapp_fragments.Database.Questions
import com.example.quizapp_fragments.Database.QuestionsData
import com.example.quizapp_fragments.Database.QuestionsMCQ
import com.example.quizapp_fragments.Database.Questions_McqData
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.MVC.QuizAppModel
import com.example.quizapp_fragments.MVC.QuizAppView
import com.example.quizapp_fragments.R
import java.util.*


class NavigationQuestionFragment: Fragment() {

    private lateinit var quizAppView: QuizAppView
    private lateinit var inf: View
    private var questionTextView: TextView?=null
    private var questionTextViewMCQ: TextView?=null
//    private lateinit var questionsTF:List<QuizAppModel.TFQuizQuestion>
    private lateinit var questionsTF:List<Questions>
//    private lateinit var questionsMCQ:List<QuizAppModel.MCQQuizQuestion>
private lateinit var questionsMCQ:List<QuestionsMCQ>
    private lateinit var optsforMCQ:List<RadioButton>
    private lateinit var optsforTF:List<RadioButton>
    private lateinit var optsforTFgrp:RadioGroup
    private lateinit var optsforMCQgrp:RadioGroup
//    var  position: Int? = null

    override fun onCreateView(
                    inflater: LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?
    ): View? {
        inf = inflater.inflate(R.layout.navigate_question_fragment, container, false)

        val context = activity as QuizAppController

        val fragment = activity?.supportFragmentManager?.findFragmentById(R.id.flcontainer1)

        /*Here ,it is taking the view from the fragments since this navigationFragment
        dont have the views of TFfragment or MCQFragment.So ,that on clicking the button in
        this fragment it can navigate .
         */
        if (fragment is TFfragment) {
            questionTextView = fragment.getQuestionText()
            optsforTF=fragment.getOptionsTF()
            optsforTFgrp=fragment.getOptionsforgrp()
        } else if (fragment is MCQfragment) {
            questionTextViewMCQ = fragment.getQuestionText()
            optsforMCQ=fragment.getOptionsId()
            optsforMCQgrp=fragment.getOptionsforgrp()
        }

//        val getFragment=HomeFragment()
//        position=getFragment.position
//        getFragment.getPositions()
//        questionsTF=context.TFquestions
        val position=arguments?.getInt("key")
        Log.d("Positon1",position.toString())
//        Log.d("Positions",position.toString())

        /*
        Here ,The data is taking from the RoomData Base
         */

        val questionsData= QuestionsData()
//        questionsTF= position?.let { questionsData.dataQuestions(requireContext(), it ) } as List<Questions>
//        Log.d("QuestionsAfter",questionsTF.toString())
        val defaultPosition=kotlin.random.Random.nextInt(0,10)
        questionsTF = questionsData.dataQuestions(requireContext(), position ?: defaultPosition) as List<Questions>

//        questionsMCQ=context.MCQquestionsData
        val questionsDataMCQ=Questions_McqData()
//        questionsMCQ= position.let { questionsDataMCQ.dataQuestions_Mcq(requireContext(), it) } as List<QuestionsMCQ>
        questionsMCQ = questionsDataMCQ.dataQuestions_Mcq(requireContext(), position ?: defaultPosition) as List<QuestionsMCQ>
        quizAppView= QuizAppView()

        /*
        Here Again The Handlers are doing their functionality according to the option selected
         */

        if (context.selectedOption == R.id.tfOption || context.selectedOption==R.id.defTfOption  ) {
            quizAppView.onClickFunctionalityTF(context,inf, questionsTF, questionTextView,optsforTF,optsforTFgrp)
        }
        else {
                quizAppView.onClickFunctionalityMCQ(context,inf,questionsMCQ,questionTextViewMCQ,optsforMCQ,optsforMCQgrp)
        }
        return inf
    }

}



