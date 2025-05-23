package com.imthiyas.notes_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavArgs
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.imthiyas.notes_app.R
import com.imthiyas.notes_app.databinding.FragmentUpdateNoteBinding
import com.imthiyas.notes_app.model.Note
import com.imthiyas.notes_app.ui.MainActivity
import com.imthiyas.notes_app.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(), MenuProvider {


    private var updateNoteBinding: FragmentUpdateNoteBinding? = null
    private val binding get() = updateNoteBinding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote: Note
    private val args: UpdateNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateNoteBinding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note


        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDescription)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()


            if (noteTitle.isNotEmpty()) {
                val note = Note(currentNote.id, noteTitle, noteDesc)
                noteViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment, false)
            } else {
                Toast.makeText(context, "Please Enter Note title", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_update_note, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteNote()
                true
            }
            else -> false
        }
    }


    private fun deleteNote() {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Delete Note")
                setMessage("Do You Want to Delete Note")
                    .setPositiveButton("Yes") { _, _ ->
                        noteViewModel.deleteNote(currentNote)
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT)
                            .show()
                        view?.findNavController()?.popBackStack(R.id.homeFragment, false)
                    }
                    .setNegativeButton("No", null)
            }.create()
                .show()
        }
    }

}