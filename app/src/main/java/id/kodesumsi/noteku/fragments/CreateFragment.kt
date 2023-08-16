package id.kodesumsi.noteku.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.kodesumsi.noteku.R
import id.kodesumsi.noteku.adapter.NoteAdapter
import id.kodesumsi.noteku.model.Note
import id.kodesumsi.noteku.repository.NoteRepository

class CreateFragment : Fragment() {

    private lateinit var repository : NoteRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = NoteRepository(requireActivity().application)

        val addButton : Button = view.findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val titleField : EditText = view.findViewById(R.id.titlefield)
            val descriptionField : EditText = view.findViewById(R.id.descriptionfield)
            val title = titleField.text.toString()
            val description = descriptionField.text.toString()
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in the forms", Toast.LENGTH_SHORT).show()
            } else {
                repository.insertNewNote(Note(0,title,description))
                Toast.makeText(requireContext(), "Note successfully added", Toast.LENGTH_SHORT).show()
                titleField.text = null
                descriptionField.text = null
            }
        }
    }
}