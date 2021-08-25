package com.bccowo.naiz.presentation.detail_candi.ornament

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.OrnamentAdapter
import com.bccowo.naiz.databinding.FragmentOrnamentBinding
import com.bccowo.naiz.domain.model.Relief
import com.bccowo.naiz.presentation.detail_ornament.DetailOrnamentActivity

class OrnamentFragment : Fragment() {
    companion object {
        private const val EXTRA_RELIEF = "EXTRA_RELIEF"
        private const val EXTRA_CANDI_ID = "EXTRA_CANDI_D"

        fun createInstance(reliefs: List<Relief>, candiId: Int): OrnamentFragment {
            return OrnamentFragment().apply {
                val arrayList = arrayListOf<Relief>().apply {
                    addAll(reliefs)
                }
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_RELIEF, arrayList)
                    putInt(EXTRA_CANDI_ID, candiId)
                }
            }
        }
    }
    private var _binding: FragmentOrnamentBinding? = null
    private val binding get() = _binding!!

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
            val candiId = arguments?.getInt(EXTRA_CANDI_ID, 0) as Int
            val intent = Intent(context, DetailOrnamentActivity::class.java)
            intent.putExtra(DetailOrnamentActivity.EXTRA_ORNAMENT, it)
            intent.putExtra(DetailOrnamentActivity.EXTRA_CANDI_ID, candiId)
            startActivity(intent)
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