package com.example.signinappsample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signinappsample.Database.Note
import com.example.signinappsample.R
import com.example.signinappsample.adapter.NoteListAdapter
import com.example.signinappsample.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.note_fragment.*
import java.util.*

class NoteDatabaseFragment : Fragment(), View.OnClickListener, NoteListAdapter.DeleteSelectedData {
    private lateinit var viewModel: NoteViewModel
    private var addData: String? = null
    private var note: Note? = null
    private var id: String? = null
    private var noteListAdapter: NoteListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        setRecyclerView()
        setListeners()
    }

    private fun observeLiveData() {
        viewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        viewModel.getAllNote()?.observeForever {
            noteListAdapter?.setData(it)
        }
    }

    private fun setRecyclerView() {
        noteListAdapter = NoteListAdapter(this)
        recyclerview.setAdapter(noteListAdapter)
        recyclerview.setLayoutManager(LinearLayoutManager(context))
    }

    private fun setListeners() {
        button.setOnClickListener(this)
        enterNote.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button -> {
                note = Note(id!!, addData!!)
                note?.let {
                    viewModel.insert(it)
                }
                Toast.makeText(
                    activity?.applicationContext,
                    note?.getNoteValueAdded().toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.enterNote -> {
                id = UUID.randomUUID().toString()
                addData = enterNote.text.toString()
            }
            R.id.deleteButton -> {
                viewModel.deleteAll()
                noteListAdapter?.notifyDataSetChanged()
            }

        }
    }

    override fun deleteSelectedData(id: String) {
        if (!id.isNullOrEmpty()) {
            viewModel.deleteById(id)
        }
    }
}