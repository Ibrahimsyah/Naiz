package com.bccowo.naiz.presentation.detail_candi.ornament

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.databinding.FragmentOrnamentBinding
import com.bccowo.naiz.domain.usecase.CandiUseCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrnamentFragment : Fragment() {

    private var _binding: FragmentOrnamentBinding? = null
    private val binding get() = _binding!!
    private val ornamentViewModel: OrnamentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrnamentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ornamentAdapter = OrnamentAdapter {
            if (it.isFound) {

            } else {
                Toast.makeText(context, "Temukan Relief untuk Membuka", Toast.LENGTH_SHORT).show()
            }
        }
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }

        ornamentViewModel.getOrnamentList(0).observe(viewLifecycleOwner, {
            ornamentAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}