package com.imthiyas.notes_app.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imthiyas.notes_app.databinding.NoteLayoutBinding
import com.imthiyas.notes_app.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(val noteLayoutBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(noteLayoutBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    return oldItem.noteTitle == newItem.noteTitle &&
                            return oldItem.noteDescription == newItem.noteDescription
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


}