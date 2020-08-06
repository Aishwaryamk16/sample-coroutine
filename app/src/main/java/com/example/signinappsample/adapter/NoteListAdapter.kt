package com.example.signinappsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.signinappsample.Database.Note
import com.example.signinappsample.R

class NoteListAdapter(private val listener: DeleteSelectedData) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var noteList: List<Note>? = null

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(
            noteList: String,
            listener: DeleteSelectedData,
            id: String
        ) {
            val noteText = itemView.findViewById<TextView>(R.id.txvNote)
            val deleteImage = itemView.findViewById(R.id.delete) as ImageView
            noteText.text = noteList
            deleteImage.setOnClickListener {
                listener.deleteSelectedData(id)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return noteList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NoteListAdapter.NoteViewHolder, position: Int) {
        holder.setData(noteList!!.get(position).note, listener, noteList!!.get(position).id)
    }

    fun setData(it: List<Note>) {
        noteList = it
        notifyDataSetChanged()
    }

    interface DeleteSelectedData {
        fun deleteSelectedData(id: String)
    }
}