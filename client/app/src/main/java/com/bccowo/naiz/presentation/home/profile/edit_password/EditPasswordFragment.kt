package com.bccowo.naiz.presentation.home.profile.edit_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bccowo.naiz.databinding.FragmentEditPasswordBinding

class EditPasswordFragment : Fragment() {

    private var _binding : FragmentEditPasswordBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditPasswordBinding.inflate(inflater)
        val navController = findNavController()
        with(binding.toolbar) {
            NavigationUI.setupWithNavController(this, navController)
            setHasOptionsMenu(true)
            (activity as AppCompatActivity).setSupportActionBar(this)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
            setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        return binding.root
    }
}