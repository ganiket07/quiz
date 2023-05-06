package com.example.quizapp_fragments.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizapp_fragments.Adapters.SubjectCellListener
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.R
import com.google.android.material.snackbar.Snackbar

class HomeFragment:Fragment(),SubjectCellListener {
    private lateinit var startQuizButton: Button
    private lateinit var options: RadioGroup
    private lateinit var navigationFragment: Fragment
//    var position: Int?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.homefragment, container, false)
        startQuizButton = inf.findViewById(R.id.startQuizButton)
        options = inf.findViewById(R.id.optionsRadioGroup)
        val context =activity as QuizAppController
        val tfFragment=TFfragment()
        val mcqFragment=MCQfragment()
        navigationFragment=NavigationQuestionFragment()


        val position=arguments?.getInt("key")
        Log.d("Positions",position.toString())

        val args = Bundle()
        args.putInt("key", position!!)


        /*
        When the start Button is clicked then it goes to the next fragment according to the options
         selected by the user
         It checks with id of the option
         */
        startQuizButton.setOnClickListener {
            context.selectedOption = options.checkedRadioButtonId
            if (context.selectedOption == R.id.tfOption){
                replaceFragment(tfFragment,navigationFragment)
                //For accessing the position in the next fragment ,using bundle to send the position
                navigationFragment.arguments = args
            }
            else if (context.selectedOption == R.id.mcqOption){
                replaceFragment(mcqFragment,navigationFragment)
                //For accessing the position in the next fragment ,using bundle to send the position
                navigationFragment.arguments = args
            }

        }


        return inf
    }


    /*
    Using the function replace Fragment to replace the fragments
     */

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