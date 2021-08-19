package com.bccowo.naiz.presentation.home.candilist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.FragmentCandiListBinding
import com.bccowo.naiz.domain.model.Candi
import org.koin.androidx.viewmodel.ext.android.viewModel

class CandiListFragment : Fragment() {

    private lateinit var binding : FragmentCandiListBinding
    private val candiListViewModel: CandiListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCandiListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        val candiListAdapter = CandiListAdapter { onCandiClick(it) }
        with(binding.rvListCandi) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = candiListAdapter
        }

        binding.searchCandi.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                candiListViewModel.searchCandi(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })

        candiListViewModel.getCandiList().observe(viewLifecycleOwner, {
            candiListAdapter.setData(it)
            binding.searchNotFound.root.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })

        candiListViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.searchNotFound.root.visibility = View.GONE
                binding.pbCandi.visibility = View.VISIBLE
            } else {
                binding.pbCandi.visibility = View.GONE
            }
        })

    }

    private fun onCandiClick(candi: Candi) {
        Toast.makeText(context, "Clicked ${candi.name}", Toast.LENGTH_SHORT).show()
    }
}