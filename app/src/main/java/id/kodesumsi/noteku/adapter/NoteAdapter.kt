package id.kodesumsi.noteku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.kodesumsi.noteku.EditActivity
import id.kodesumsi.noteku.MainActivity
import id.kodesumsi.noteku.R
import id.kodesumsi.noteku.model.Note
import id.kodesumsi.noteku.repository.NoteRepository

class NoteAdapter(private val items: List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val txtName : TextView = itemView.findViewById(R.id.txt_name)
        private val txtDescription : TextView = itemView.findViewById(R.id.txt_description)

        fun bindItem(note: Note) {
            txtName.text = note.title
            txtDescription.text = note.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditActivity::class.java)
                intent.putExtra("id", note.id)
                intent.putExtra("title", note.title)
                intent.putExtra("description", note.description)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

}