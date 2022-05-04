package com.example.semkanotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.semkanotes.Database.Note
import com.example.semkanotes.notesSemka.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEdit : AppCompatActivity() {
    lateinit var editTitle:EditText
    lateinit var editDescription:EditText
    lateinit var editUpdateButt: Button
    lateinit var viewModel: NoteViewModel

    var id = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        editTitle = findViewById(R.id.editNote)
        editDescription = findViewById(R.id.editDescription)
        editUpdateButt = findViewById(R.id.buttonUpdate)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("titleNote")
            val noteDesc = intent.getStringExtra("descNote")
            id = intent.getIntExtra("id",-1)
            editUpdateButt.setText("Uložiť")
            editTitle.setText(noteTitle)
            editDescription.setText(noteDesc)
        }
        else{
            editUpdateButt.setText("Pridať")
        }

        editUpdateButt.setOnClickListener{
            val title = editTitle.text.toString()
            val desc = editDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (title.isNotEmpty() && desc.isNotEmpty()){
                    val dateFormat = SimpleDateFormat("dd mm yyyy - HH:mm")
                    val date:String = dateFormat.format(Date())
                    val uppdateNote = Note(title,desc,date)
                    uppdateNote.id = id
                    viewModel.updateNote(uppdateNote)
                    Toast.makeText(this,"Záznam bol aktualizovaný", Toast.LENGTH_LONG).show()
                }
            } else {
               if ( title.isNotEmpty() && desc.isNotEmpty()) {
                   val dateFormat = SimpleDateFormat("dd mm yyyy - HH::mm")
                   val date:String = dateFormat.format(Date())
                   viewModel.addNote(Note(title,desc,date))
                   Toast.makeText(this,"Bol pridaný nový záznam", Toast.LENGTH_LONG).show()
               }
            }

            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }

    }
}