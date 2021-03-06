package com.bccowo.naiz.presentation.home.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.bccowo.naiz.R
import com.bccowo.naiz.core.util.GreetingGenerator
import com.bccowo.naiz.databinding.FragmentHomeBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.presentation.detail_candi.DetailCandiActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularCandiAdapter: PopularCandiAdapter
    private lateinit var progressCandiAdapter: ProgressCandiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularCandiAdapter = PopularCandiAdapter(
            homeViewModel, viewLifecycleOwner,
            { candi -> onPopularCandiClick(candi) },
            { candi, bookmarked -> onCandiBookmarkChange(candi, bookmarked) })
        progressCandiAdapter = ProgressCandiAdapter { onProgressCandiClick(it) }

        with(binding.rvPopularCandi) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularCandiAdapter
        }
        with(binding.rvProgressCandi) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = progressCandiAdapter
        }

        val greeting = GreetingGenerator.generateGreeting(context as Context)
        binding.homeGreeting.text = greeting
        binding.userName.text = homeViewModel.userName

        binding.userPhoto.load(homeViewModel.userPhoto) {
            transformations(CircleCropTransformation())
        }

        homeViewModel.getPopularCandi().observe(viewLifecycleOwner, {
            popularCandiAdapter.setData(it)
        })
        homeViewModel.getCandiProgress().observe(viewLifecycleOwner, {
            progressCandiAdapter.setData(it)
        })

        binding.btnShowMore.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_candiListFragment)
        }

        binding.btnBookmark.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_bookmarkFragment)
        }

        binding.userPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_profile)
        }
    }
    private fun onProgressCandiClick(candiProgress: Candi) {
        Toast.makeText(context, "Clicked ${candiProgress.name}", Toast.LENGTH_SHORT).show()
    }

    private fun onPopularCandiClick(candi: Candi) {
        val intent = Intent(context, DetailCandiActivity::class.java)
        intent.putExtra(DetailCandiActivity.EXTRA_CANDI_DETAIL, candi)
        startActivity(intent)
    }

    private fun onCandiBookmarkChange(candi: Candi, bookmarked: Boolean) {
        val message = if (bookmarked) {
            homeViewModel.addCandiToBookmark(candi)
            getString(R.string.candi_added_successfully)
        } else {
            homeViewModel.removeCandiFromBookmark(candi)
            getString(R.string.candi_deleted_successfully)
        }
        Toast.makeText(
            context, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}