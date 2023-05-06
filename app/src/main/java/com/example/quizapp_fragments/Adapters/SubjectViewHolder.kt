package com.example.quizapp_fragments.Adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.R

class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val subjectNameTextView: TextView = itemView.findViewById(R.id.textView_subject_name)

    fun bind(subject: String) {
        subjectNameTextView.text = subject
    }
}
