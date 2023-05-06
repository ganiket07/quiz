package com.example.quizapp_fragments.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_fragments.Fragments.SubjectListFragment
import com.example.quizapp_fragments.R

class SubjectAdapter(private val context:SubjectListFragment, private val subjectCellListener: SubjectCellListener) : RecyclerView.Adapter<SubjectViewHolder>() {

    private val subjects = listOf(
                "Geography",
                "History",
                "Science",
                "Literature",
                "Art and Culture",
                "Music",
                "Sports",
                "Movies and TV",
                "Technology",
                "General Knowledge"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inf = LayoutInflater.from(parent.context).inflate(R.layout.subjects_items, parent, false)
        return SubjectViewHolder(inf)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjects[position])
        holder.itemView.setOnClickListener {
            subjectCellListener.onSubjectCellListener(it,position)
        }

    }

    override fun getItemCount() = subjects.size
}
