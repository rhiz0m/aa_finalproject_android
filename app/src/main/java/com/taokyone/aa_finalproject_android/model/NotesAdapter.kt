package com.taokyone.aa_finalproject_android.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taokyone.aa_finalproject_android.databinding.UsersNotesItemBinding
import com.taokyone.aa_finalproject_android.view.NotesFragment
import java.util.ArrayList

class NotesAdapter(
    var context: NotesFragment,
    var notesList: ArrayList<UsersNotes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val adapterBinding: UsersNotesItemBinding) : RecyclerView.ViewHolder(adapterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val viewBinding = UsersNotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {

        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       holder.adapterBinding.tvQuotes.text = notesList[position].quotes
        holder.adapterBinding.tvTitle.text = notesList[position].title
        holder.adapterBinding.tvDateItem.text = notesList[position].date.toString()
        holder.adapterBinding.tvReflections.text = notesList[position].reflection

    }

}