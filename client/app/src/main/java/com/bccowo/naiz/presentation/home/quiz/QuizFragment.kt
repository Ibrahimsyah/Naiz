package com.bccowo.naiz.presentation.home.quiz

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.DialogQuizBinding
import com.bccowo.naiz.databinding.FragmentQuizBinding
import com.bccowo.naiz.domain.model.Quiz
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment : Fragment() {

    private val quizViewModel: QuizViewModel by viewModel()
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quizAdapter = QuizAdapter {
            when (it.status) {
                "playable" -> playQuiz(it)
                "locked" -> showErrorQuizLocked()
                else -> promptRetakeQuiz(it)
            }
        }
        with(binding.rvQuiz) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = quizAdapter
        }

        quizViewModel.getQuiz().observe(viewLifecycleOwner, {
            quizAdapter.setData(it)
        })
    }

    private fun playQuiz(quiz: Quiz) {

    }

    private fun showErrorQuizLocked() {
        Toast.makeText(context, getString(R.string.quiz_locked_message), Toast.LENGTH_SHORT).show()
    }

    private fun promptRetakeQuiz(quiz: Quiz) {
        val view = DialogQuizBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context).apply {
            setView(view.root)
            setCancelable(false)
        }
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.btnConfirm.setOnClickListener {
            playQuiz(quiz)
            alertDialog.cancel()
        }
        view.btnCancel.setOnClickListener { alertDialog.cancel() }
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}