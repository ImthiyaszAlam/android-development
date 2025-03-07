package com.imthiyas.hilt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class UserFragment : Fragment() {


    @Inject
    private lateinit var userRepository: UserRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userRepository.saveUser("user2", "pass2")
        return inflater.inflate(R.layout.fragment_user, container, false)


    }

}