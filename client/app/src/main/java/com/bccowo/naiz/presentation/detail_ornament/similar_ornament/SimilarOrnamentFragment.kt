package com.bccowo.naiz.presentation.detail_ornament.similar_ornament

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.core.ui.OrnamentAdapter
import com.bccowo.naiz.databinding.FragmentSimilarOrnamentBinding
import com.bccowo.naiz.presentation.detail_ornament.DetailOrnamentActivity
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
            if (it.isFound) {
                val intent = Intent(activity, DetailOrnamentActivity::class.java)
                intent.putExtra(DetailOrnamentActivity.EXTRA_ORNAMENT_ID, it.id)
                startActivity(intent)
                activity?.finish()
            } else {
                Toast.makeText(context, "Temukan Relief untuk Membuka", Toast.LENGTH_SHORT).show()
            }
        }
        with(binding.rvOrnament) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ornamentAdapter
        }

        similarOrnamentViewModel.getSimilarOrnament(0).observe(viewLifecycleOwner, {
            ornamentAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}