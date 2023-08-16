package id.kodesumsi.noteku.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kodesumsi.noteku.R
import id.kodesumsi.noteku.adapter.NoteAdapter
import id.kodesumsi.noteku.model.Note
import id.kodesumsi.noteku.repository.NoteRepository
import org.w3c.dom.Text

class ListFragment : Fragment() {

    private lateinit var rv : RecyclerView

    private lateinit var rvAdapter: NoteAdapter

    private lateinit var notes : List<Note>

    private lateinit var repository : NoteRepository

    private lateinit var notesCount : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesCount =  view.findViewById(R.id.notes_count)

        repository = NoteRepository(requireActivity().application)

        initComponents(view)
        refreshList()
    }

    private fun initComponents(view: View) {
        rv = view.findViewById(R.id.rv_view)
    }

    private fun addItem() {
        notes = repository.getAll()
    }

    private fun refreshList() {
        addItem()
        setNoteCount()
        rvAdapter = NoteAdapter(notes)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setNoteCount() {
        val count = notes.size
        if (count <= 0) {
            notesCount.text = "Start writing your notes!"
        } else {
            notesCount.text = count.toString() + " Notes"
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

}