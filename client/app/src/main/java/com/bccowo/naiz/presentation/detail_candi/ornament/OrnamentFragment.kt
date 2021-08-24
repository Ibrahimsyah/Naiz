package com.bccowo.naiz.presentation.detail_candi.ornament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.OrnamentAdapter
import com.bccowo.naiz.databinding.FragmentOrnamentBinding
import com.bccowo.naiz.domain.model.Relief
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrnamentFragment : Fragment() {
    companion object {
        private const val EXTRA_RELIEF = "EXTRA_RELIEF"

        fun createInstance(reliefs: List<Relief>): OrnamentFragment {
            return OrnamentFragment().apply {
                val arrayList = arrayListOf<Relief>().apply {
                    addAll(reliefs)
                }
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_RELIEF, arrayList)
                }
            }
        }
    }
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
        }
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }

        val reliefs = arguments?.getParcelableArrayList<Relief>(EXTRA_RELIEF)
        reliefs?.let {
            ornamentAdapter.setData(it.toList())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}