package com.example.quizapp_fragments.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.Fragments.HistoryFragment
import com.example.quizapp_fragments.R

class AttemptsAdapter(private val historyFragment: HistoryFragment, private val scorelist: MutableList<String>, private val wrongansList: MutableList<String>, private val attemptsList: MutableList<List<Int>>): RecyclerView.Adapter<AttemptsViewHolder>() {

    /*
    Concatinating the scorelist and wrongAns List so that it can bind to the recycler view
     */
   fun getlist(): MutableList<MutableList<String>> {
       val concatenatedList = mutableListOf<MutableList<String>>()
        for (i in 0 until scorelist.size) {
        val subList = mutableListOf<String>()
        subList.add(scorelist[i])
        subList.add(wrongansList[i])
        subList.add(attemptsList[i].toString())
        concatenatedList.add(subList)
    }
    return concatenatedList
     }
    var lists=getlist()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttemptsViewHolder {
        val inf = LayoutInflater.from(parent.context).inflate(R.layout.attempts_items, parent, false)
        // In this class the datas i.e scores and wrongans is getting binded with the view i.e attempts_items
        return AttemptsViewHolder(inf)
    }

    override fun onBindViewHolder(holder: AttemptsViewHolder, position: Int) {
        holder.bind(lists[position])
    }
    override fun getItemCount(): Int {
        val list=getlist()
        Log.d("Size of list",list.size.toString())
        return list.size
    }
}