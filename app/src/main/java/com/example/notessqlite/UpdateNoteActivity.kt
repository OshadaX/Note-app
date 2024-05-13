package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId:Int=-1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NotesDatabaseHelper(this)

        noteId=intent.getIntExtra("note_id",-1)
        if (noteId==-1){
            finish()
            return
        }
        // Retrieve the note from the database
        val note = db.getNoteByID(noteId)

        // Set the title and content of the note to EditText fields
        binding.updatetitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        // Set OnClickListener for the save button
        binding.updateSaveButton.setOnClickListener {
            // Get the new title and content from EditText fields
            val newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()

            // Create a new Note object with the updated title and content
            val updatedNote = Note(noteId, newTitle, newContent)

            // Update the note in the database
            db.updateNote(updatedNote)

            // Finish the activity
            finish()

            // Show a toast message indicating that changes are saved
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }


}