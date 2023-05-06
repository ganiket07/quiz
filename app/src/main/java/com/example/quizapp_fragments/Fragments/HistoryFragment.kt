package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.Adapters.AttemptsAdapter
import com.example.quizapp_fragments.R
import com.google.gson.Gson

class HistoryFragment:Fragment() {
    @SuppressLint("MissingInflatedId")

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AttemptsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf=inflater.inflate(R.layout.recyclerview_attempts,container,false)

       /*
        Accessing the data through sharedPreferences and using it for the recyclerView
        */
        val sharedPreferences = requireContext().getSharedPreferences("quiz-result", Context.MODE_PRIVATE)
        val scoresList = mutableListOf<String>()
        val wrongansList = mutableListOf<String>()
//        val attemptsList= mutableListOf<String>()
        val attemptsList = mutableListOf<List<Int>>()
        val noOfAttempts = sharedPreferences.getInt("noOfAttempts", 0)
        Log.d("noOfAttempts", noOfAttempts.toString())

        for (i in 1..noOfAttempts) {
            val scoreJson = sharedPreferences.getString("attempt_$i", null)
            val wrongJson = sharedPreferences.getString("Wrong attempt_$i", null)
            val attemptsJson = sharedPreferences.getString("AttemptsNo_$i", null)
            if (attemptsJson != null) {
                val attempts = if (attemptsJson.startsWith("[")) {
                    Gson().fromJson(attemptsJson, Array<Int>::class.java).toList()
                } else {
                    listOf(attemptsJson.toInt())
                }
                attemptsList.add(attempts)
            }
            if (scoreJson != null) {
                val score = Gson().fromJson(scoreJson, scoreJson::class.java)
                scoresList.add(score.toString())
            }
            if (wrongJson != null) {
                val wrongans = Gson().fromJson(wrongJson, wrongJson::class.java)
                wrongansList.add(wrongans.toString())
            }
        }


        Log.d("Score list", scoresList.toString())
        Log.d("Wrong List", wrongansList.toString())
        Log.d("Attempts List", attemptsList.toString())


        recyclerView = inf.findViewById(R.id.recyclerView)
        adapter = AttemptsAdapter(this,scoresList,wrongansList,attemptsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        return inf
    }
}
