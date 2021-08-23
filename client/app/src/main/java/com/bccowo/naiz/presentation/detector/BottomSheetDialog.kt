package com.bccowo.naiz.presentation.detector

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.BottomSheetLayoutBinding
import com.bccowo.naiz.domain.model.Candi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog private constructor(
    private val supportFragmentManager: FragmentManager,
    private val onCancel: () -> Unit
) :
    BottomSheetDialogFragment() {
    companion object {
        fun getInstance(
            supportFragmentManager: FragmentManager,
            onCancel: () -> Unit,
        ): BottomSheetDialog {
            return BottomSheetDialog(supportFragmentManager, onCancel)
        }
    }

    private lateinit var list: List<Candi>
    private lateinit var cb: (Candi) -> Boolean
    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val candi = list.map { it.name }
        ArrayAdapter(
            context as Context,
            R.layout.support_simple_spinner_dropdown_item,
            candi
        ).apply {
            binding.candiSpinner.adapter = this
        }
        binding.btnSubmit.setOnClickListener {
            val index = binding.candiSpinner.selectedItemPosition
            val selectedCandi = list[index]
            cb(selectedCandi)
        }
    }

    fun showData(list: List<Candi>, cb: (Candi) -> Boolean) {
        this.list = list
        this.cb = cb
        this.show(supportFragmentManager, "dialog")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancel()
    }
}