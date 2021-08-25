package com.bccowo.naiz.core.ui.similar_ornament

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
    companion object {
        private const val EXTRA_RELIEF_NAME = "EXTRA_RELIEF_NAME"

        fun createInstance(reliefName: String): SimilarOrnamentFragment {
            return SimilarOrnamentFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_RELIEF_NAME, reliefName)
                }
            }
        }
    }

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

        val reliefName = arguments?.getString(EXTRA_RELIEF_NAME) as String
        val ornamentAdapter = OrnamentAdapter {}
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }

        similarOrnamentViewModel.getSimilarOrnament(reliefName).observe(viewLifecycleOwner, {
            ornamentAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}