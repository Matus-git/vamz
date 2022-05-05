package com.example.semkanotes.Aktivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semkanotes.Database.Note
import com.example.semkanotes.R
import com.example.semkanotes.notesSemka.NoteAdapter
import com.example.semkanotes.notesSemka.NoteClickDeleteInterface
import com.example.semkanotes.notesSemka.NoteClickInterface
import com.example.semkanotes.notesSemka.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Trieda predstavuje hlavnú aktivitu
 *
 */
class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface, SearchView.OnQueryTextListener {

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
        viewModel.notes.observe(this,{ list ->
            list?.let{
                noteAdapter.updateNoteList(it)
            }
        })
        addButt.setOnClickListener {
            val intent = Intent(this, AddEdit::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEdit::class.java)
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchNotes(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNotes(newText)
        }
        return true
    }

    /**
     * Funkcia pomocou
     * ktoréj vyhĺadávame a zobrazujeme  jednotlivé poznámkami
     *
     * @param search typu String
     */
    private fun searchNotes(search :String) {
        val searchQuery = "%$search%"
        val noteAdapter = NoteAdapter(this,this,this)
        notes.adapter = noteAdapter
        viewModel.searchNote(searchQuery).observe(this,{ list ->
            list?.let{
                noteAdapter.updateNoteList(it)
            }
        })

    }
}