package com.taokyone.aa_finalproject_android.model

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taokyone.aa_finalproject_android.databinding.ListItemBinding
import com.taokyone.aa_finalproject_android.view.EditActivity
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
        // Item Xml <-> UserNotes Obj

        holder.adapterBinding.tvDateItem.text = notesList[position].date
        holder.adapterBinding.tvTitleItem.text = notesList[position].title
        holder.adapterBinding.tvCategoryItem.text = notesList[position].category
        holder.adapterBinding.tvReflectionsItem.text = notesList[position].reflections

        holder.adapterBinding.usersNotesItemLayout.setOnClickListener() {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("uniqueId", notesList[position].uniqueId)
            intent.putExtra("date", notesList[position].date)
            intent.putExtra("title", notesList[position].title)
            intent.putExtra("category", notesList[position].category)
            intent.putExtra("reflections", notesList[position].reflections)
            context.startActivity(intent)
            }

        }
    override fun getItemCount(): Int {
        return notesList.size
    }
    //Function to get the Id of the item to be deleted
    fun getUniqueId (position: Int) : String {
        return notesList[position].uniqueId
    }
}

