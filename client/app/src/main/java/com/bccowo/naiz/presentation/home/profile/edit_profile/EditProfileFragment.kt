package com.bccowo.naiz.presentation.home.profile.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import coil.load
import coil.transform.CircleCropTransformation
import com.bccowo.naiz.databinding.FragmentEditProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val editProfileViewModel: EditProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userPhoto.load(editProfileViewModel.userPhoto) {
            transformations(CircleCropTransformation())
        }

        binding.edFullname.setText(editProfileViewModel.userName)
        binding.edEmail.setText(editProfileViewModel.userEmail)
        binding.edPhoneNumber.setText(editProfileViewModel.userPhone)
    }
}