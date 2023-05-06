package com.example.quizapp_fragments.Adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.R

class AttemptsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val score_textview: TextView = itemView.findViewById(R.id.Score)
    private val percent_textview: TextView = itemView.findViewById(R.id.Percentage)
    private val wrongans_textview:TextView = itemView.findViewById(R.id.wrongAns)
    private val attemptsNo_textview:TextView=itemView.findViewById(R.id.attemptsNo)

    fun bind(list: MutableList<String>) {
        val percent=(list[0].toDouble() / 5.0 * 100.0).toInt()

        score_textview.text = list[0]
        percent_textview.text = (percent.toString())
        wrongans_textview.text = list[1]
        attemptsNo_textview.text = list[2]

    }
}