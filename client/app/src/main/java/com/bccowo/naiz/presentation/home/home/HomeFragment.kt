package com.bccowo.naiz.presentation.home.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.bccowo.naiz.R
import com.bccowo.naiz.core.util.GreetingGenerator
import com.bccowo.naiz.databinding.FragmentHomeBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
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
        val dummyUserPhoto =
            "https://i.pinimg.com/736x/80/cd/66/80cd662b2d7c0ae90ff1de1be49b36aa.jpg"

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

        binding.userPhoto.load(dummyUserPhoto) {
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
    }
    private fun onProgressCandiClick(candiProgress: CandiProgress) {
        Toast.makeText(context, "Clicked ${candiProgress.name}", Toast.LENGTH_SHORT).show()
    }

    private fun onPopularCandiClick(candi: Candi) {

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