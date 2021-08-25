package com.bccowo.naiz.core.ui.other_ornament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.OrnamentAdapter
import com.bccowo.naiz.databinding.FragmentOtherOrnamentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtherOrnamentFragment : Fragment() {
    companion object {
        private const val EXTRA_CANDI_ID = "EXTRA_CANDI_ID"

        fun createInstance(candiId: Int): OtherOrnamentFragment {
            return OtherOrnamentFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_CANDI_ID, candiId)
                }
            }
        }
    }

    private var _binding: FragmentOtherOrnamentBinding? = null
    private val binding get() = _binding!!
    private val otherOrnamentViewModel: OtherOrnamentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtherOrnamentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val candiId = arguments?.getInt(EXTRA_CANDI_ID, 0) as Int
        val ornamentAdapter = OrnamentAdapter {}
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }

        otherOrnamentViewModel.getOtherRelief(candiId).observe(viewLifecycleOwner, {
            ornamentAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}