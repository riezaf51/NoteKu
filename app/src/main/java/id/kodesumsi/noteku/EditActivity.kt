package id.kodesumsi.noteku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.kodesumsi.noteku.R
import id.kodesumsi.noteku.model.Note
import id.kodesumsi.noteku.repository.NoteRepository

class EditActivity : AppCompatActivity() {

    private lateinit var editButton : Button

    private lateinit var deleteButton: Button

    private lateinit var backButton : Button

    private lateinit var titleField : EditText

    private lateinit var descriptionField : EditText

    private lateinit var repository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editButton = findViewById(R.id.buttonedit)
        deleteButton = findViewById(R.id.buttondelete)
        backButton = findViewById(R.id.buttonback)
        titleField = findViewById(R.id.titlefieldedit)
        descriptionField = findViewById(R.id.descriptionfieldedit)

        val intent = intent
        val id = intent.extras!!.getInt("id")
        val title = intent.extras!!.getString("title")
        val description = intent.extras!!.getString("description")

        titleField.setText(title)
        descriptionField.setText(description)

        repository = NoteRepository(application)

        editButton.setOnClickListener {
            val newTitle = titleField.text.toString()
            val newDescription = descriptionField.text.toString()
            if (newTitle.isEmpty() || newDescription.isEmpty()) {
                Toast.makeText(this, "Please fill in the forms", Toast.LENGTH_SHORT).show()
            } else {
                repository.updateNote(Note(id,newTitle,newDescription))
                Toast.makeText(this, "Note successfully updated", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        deleteButton.setOnClickListener {
            /*val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to delete this note?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    repository.deleteNote(id)
                    Toast.makeText(this, "Note successfully deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()*/
            repository.deleteNote(id)
            Toast.makeText(this, "Note successfully deleted", Toast.LENGTH_SHORT).show()
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }

    }
}