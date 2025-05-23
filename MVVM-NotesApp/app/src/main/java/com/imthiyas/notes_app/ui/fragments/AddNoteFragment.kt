package com.imthiyas.notes_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.imthiyas.notes_app.R
import com.imthiyas.notes_app.databinding.FragmentAddNoteBinding
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

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAddNoteBinding = null
    }

}