package com.bccowo.naiz.presentation.detail_ornament.similar_ornament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.OrnamentAdapter
import com.bccowo.naiz.databinding.FragmentSimilarOrnamentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimilarOrnamentFragment : Fragment() {

    private var _binding: FragmentSimilarOrnamentBinding? = null
    private val binding get() = _binding!!
    private val similarOrnamentViewModel: SimilarOrnamentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimilarOrnamentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ornamentAdapter = OrnamentAdapter {

        }
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}