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

/**
 * Trieda adapter slúži na nastavenie dát nášmu RecycleView ktorý
 * zobrazuje jednutlivé poznámky
 *
 * @property noteClickDeleteInterface
 * @property noteClickInterface
 */
//val context: Context,
class NoteAdapter(
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

    /**
     * Funkcia slúži na aktualizáciu zobrazených dát
     *
     * @param list typu List<Note>
     */
    fun updateNoteList(list: List<Note>) {
        allNotes.clear()
        allNotes.addAll(list)
        notifyDataSetChanged()
    }
}

/**
 * Rozhranie ktoré bude slúžiť na
 * odstránenie záznamu
 *
 */
interface NoteClickDeleteInterface{
    /**
     *Funkcia ktorá bude slúžiť na odstránenie záznamu
     *
     * @param note typu Note
     */
    fun onDeleteButtClick(note: Note)
}

/**
 * Rozhranie ktoré bude slúžiť na
 * zobrazenie záznamu
 */
interface NoteClickInterface{

    /**
     * Funkcia ktorá bude slúžiť na zobrazenie poznámky
     *
     * @param note typu Note
     */
    fun onNoteClick(note:Note)
}