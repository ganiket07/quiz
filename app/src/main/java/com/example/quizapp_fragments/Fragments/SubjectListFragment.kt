package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.Adapters.SubjectAdapter
import com.example.quizapp_fragments.Adapters.SubjectCellListener
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.R
import com.google.android.material.snackbar.Snackbar

class SubjectListFragment : Fragment(),SubjectCellListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubjectAdapter
//    private lateinit var frameLayout: FrameLayout

    /*
    In this fragment there is the recyclerView containing the list of subject ,
    Here user can choose the subject for the quiz and it
    goes to another fragment accordingly
     */

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inf = inflater.inflate(R.layout.recyclerview_subjects, container, false)

        recyclerView = inf.findViewById(R.id.recyclerView)
        adapter = SubjectAdapter(this,this)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

//        val fragmentManager = childFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        val fragmentToHide = fragmentManager.findFragmentById(R.id.flcontainer2)
//        if (fragmentToHide is NavigationQuestionFragment) {
//            fragmentTransaction.hide(fragmentToHide)
//            fragmentTransaction.commit()
//        }
//        val flcontainer2 = inf.findViewById<FrameLayout>(R.id.flcontainer2)
//        if (flcontainer2 != null) {
//            flcontainer2.visibility = View.GONE
//        }
//        frameLayout=inf.findViewById(R.id.flcontainer2)
//        if(frameLayout  !=  null) {
//            frameLayout.visibility = View.GONE
//        }
        return inf
    }
//    fun hideFrameLayout() {
//        frameLayout.visibility = View.GONE
//    }

    override fun onSubjectCellListener(view: View, position: Int) {
        view.let {
            val args = Bundle()
            args.putInt("key", position)
            Log.d("Positon0",position.toString())
                val homeFragment=HomeFragment()
                replaceFragments(homeFragment)
            homeFragment.arguments = args

        }
    }
    fun replaceFragments(fragment: Fragment){

        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flcontainer1,fragment)
        fragmentTransaction.addToBackStack(
            null
        )
        fragmentTransaction.commit()

//        frameLayout.visibility = View.GONE
    }

}
