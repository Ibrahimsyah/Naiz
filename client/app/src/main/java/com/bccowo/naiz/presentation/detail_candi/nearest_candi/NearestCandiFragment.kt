package com.bccowo.naiz.presentation.detail_candi.nearest_candi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.CandiListAdapter
import com.bccowo.naiz.databinding.FragmentNearestCandiBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NearestCandiFragment : Fragment() {
    companion object {
        private const val EXTRA_CANDI_ID = "EXTRA_CANDI_ID"
        fun getInstance(candiId: Int): NearestCandiFragment {
            return NearestCandiFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_CANDI_ID, candiId)
                }
            }
        }
    }

    private val nearestCandiViewModel: NearestCandiViewModel by viewModel()
    private lateinit var binding: FragmentNearestCandiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNearestCandiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nearestCandiAdapter = CandiListAdapter {}

        val candiId = arguments?.getInt(EXTRA_CANDI_ID, 0) as Int

        with(binding.rvNearestCandi) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = nearestCandiAdapter
        }

        nearestCandiViewModel.getCandiList(candiId).observe(viewLifecycleOwner, {
            nearestCandiAdapter.setData(it)
        })
    }
}