package com.example.semkanotes.notesSemka

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semkanotes.Database.Note
import com.example.semkanotes.R
import java.util.ArrayList

class NoteAdapter(val context: Context,
                  val noteClickDeleteInterface: NoteClickDeleteInterface ,
                  val noteClickInterface: NoteClickInterface ) :RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

                        private val allNotes = ArrayList<Note>()

                      inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
                          val noteVW = itemView.findViewById<TextView>(R.id.noteTitle)
                          val timeStampVW = itemView.findViewById<TextView>(R.id.noteTimestamp)
                          val delButtVW = itemView.findViewById<ImageView>(R.id.deleteButt)
                      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.note_inspect,parent,false);
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteVW.setText(allNotes.get(position).titleNote)
        holder.timeStampVW.setText("Posledná aktualizácia: " + allNotes.get(position).timeStamp)
        holder.delButtVW.setOnClickListener{
            noteClickDeleteInterface.onDeleteButtClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateLst(list: List<Note>) {
        allNotes.clear()
        allNotes.addAll(list)
        notifyDataSetChanged()
    }
}


interface NoteClickDeleteInterface{
    fun onDeleteButtClick(note: Note)
}

interface NoteClickInterface{
   fun onNoteClick(note:Note)
}