package com.bccowo.naiz.presentation.home.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_LOCALE_SETTINGS
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
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
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
        with(binding) {
            ornamentCount.text = "0"
            quizCount.text = "0"
        }

        binding.userPhoto.load(profileViewModel.userPhoto) {
            transformations(CircleCropTransformation())
        }
        binding.userName.text = profileViewModel.userName

        binding.menuEditPassword.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_edit_password)
        }

        binding.menuEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_edit_profile)
        }

        binding.menuEditLanguage.setOnClickListener {
            val intent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}