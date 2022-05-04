package com.example.semkanotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semkanotes.Database.Note
import com.example.semkanotes.notesSemka.NoteAdapter
import com.example.semkanotes.notesSemka.NoteClickDeleteInterface
import com.example.semkanotes.notesSemka.NoteClickInterface
import com.example.semkanotes.notesSemka.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var notes: RecyclerView
    lateinit var addButt: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes = findViewById(R.id.rvNotes)
        addButt = findViewById(R.id.buttAddNote)

        notes.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this,this,this)
        notes.adapter = noteAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this,{ list ->
            list?.let{
                noteAdapter.updateLst(it)
            }
        })
        addButt.setOnClickListener {
            val intent = Intent(this,AddEdit::class.java)
            startActivity(intent)
            this.finish()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//
//        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
//        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
//        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//        })
//
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,AddEdit::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("titleNote", note.titleNote)
        intent.putExtra("descNote", note.descNote)
        intent.putExtra("id",note.id)
        startActivity(intent)
    }

    override fun onDeleteButtClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.titleNote} bola odstránená", Toast.LENGTH_LONG ).show()
    }


}