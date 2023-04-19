package com.taokyone.aa_finalproject_android.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taokyone.aa_finalproject_android.databinding.ListItemBinding
import java.util.ArrayList

class NotesAdapter(
    var context: Context,
    private var notesList: ArrayList<UserNotes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val adapterBinding: ListItemBinding) : RecyclerView.ViewHolder(adapterBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.adapterBinding.tvQuotes.text = notesList[position].quotes
        holder.adapterBinding.tvTitle.text = notesList[position].title
        holder.adapterBinding.tvDateItem.text = notesList[position].date.toString()
        holder.adapterBinding.tvReflections.text = notesList[position].reflection

        }
    override fun getItemCount(): Int {
        return notesList.size
    }
}

