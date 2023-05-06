package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizapp_fragments.Database.Questions
import com.example.quizapp_fragments.Database.QuestionsMCQ
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.R

class DefaultQuizFragment: Fragment() {
//    var questions: MutableList<Questions>? = null
//    var questionsMcq:MutableList<QuestionsMCQ>?=null
    private lateinit var defStartQuizButton: Button
    private lateinit var defOptions: RadioGroup
    private lateinit var navigationFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf =inflater.inflate(R.layout.default_quizlayout,container,false)

        defStartQuizButton = inf.findViewById(R.id.defStartQuizButton)
        defOptions =inf.findViewById(R.id.defOptionsRadioGroup)
        val context =activity as QuizAppController
        val tfFragment=TFfragment()
        val mcqFragment=MCQfragment()
        navigationFragment=NavigationQuestionFragment()
        /*
        When the start Button is clicked then it goes to the next fragment according to the options
         selected by the user
         It checks with id of the option
         */
        defStartQuizButton.setOnClickListener {
            context.selectedOption = defOptions.checkedRadioButtonId
            if (context.selectedOption == R.id.defTfOption){
                replaceFragment(tfFragment,navigationFragment)
                //For accessing the position in the next fragment ,using bundle to send the position
            }
            else if (context.selectedOption == R.id.defMcqOption){
                replaceFragment(mcqFragment,navigationFragment)
                //For accessing the position in the next fragment ,using bundle to send the position
            }

        }


        return inf
    }
    fun replaceFragment(fragment1: Fragment,fragment2: Fragment){

        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flcontainer1,fragment1)
        fragmentTransaction.replace(R.id.flcontainer2,fragment2)
        fragmentTransaction.addToBackStack(
            null
        )
        fragmentTransaction.commit()

    }
}