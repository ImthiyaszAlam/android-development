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
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.imthiyas.notes_app.R
import com.imthiyas.notes_app.databinding.FragmentAddNoteBinding
import com.imthiyas.notes_app.model.Note
import com.imthiyas.notes_app.ui.MainActivity
import com.imthiyas.notes_app.viewmodel.NoteViewModel


class AddNoteFragment : Fragment(), MenuProvider {

    private var fragmentAddNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = fragmentAddNoteBinding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var addNoteView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view

    }


    private fun saveNote(view: View) {
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDesc)
            noteViewModel.addNote(note)
            Toast.makeText(addNoteView.context, "Note Saved Successfully", Toast.LENGTH_SHORT)
                .show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        } else {
            Toast.makeText(addNoteView.context, "Please Enter Note title", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }

            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAddNoteBinding = null
    }

}