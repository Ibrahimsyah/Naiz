package com.bccowo.naiz.presentation.quiz

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.EXTRAS.EXTRA_QUIZ
import com.bccowo.naiz.databinding.ActivityQuizBinding
import com.bccowo.naiz.databinding.DialogQuizBinding
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val quizEventViewModel: QuizEventViewModel by viewModel()
    private lateinit var questionData: List<QuizQuestion>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()

        val quiz = intent.getParcelableExtra<Quiz>(EXTRA_QUIZ) as Quiz
        questionData = quizEventViewModel.getQuestions()
        var questionNumber = 1
        val answerList = MutableList(questionData.size) { -1 }

        binding.quizLevel.text = String.format(getString(R.string.quiz_level), quiz.level)
        binding.quizProgress.max = questionData.size
        renderQuestion(questionNumber++)
        binding.quizChoices.setOnCheckedChangeListener { _, _ -> binding.btnNext.isEnabled = true }
        binding.btnNext.setOnClickListener {
            if (questionNumber <= questionData.size) {
                renderQuestion(questionNumber++)
                val answer = when (binding.quizChoices.checkedRadioButtonId) {
                    binding.quizChoice1.id -> 0
                    binding.quizChoice2.id -> 1
                    binding.quizChoice3.id -> 2
                    binding.quizChoice4.id -> 3
                    else -> -1
                }
                answerList.add(answer)
            } else {
                Toast.makeText(this, "Over", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderQuestion(questionNumber: Int) {
        val question = questionData[questionNumber - 1]
        with(binding) {
            quizQuestion.text = question.question
            if (question.image != null) {
                quizImageHolder.visibility = View.VISIBLE
                quizImage.load(question.image)
            } else quizImageHolder.visibility = View.GONE
            quizChoice1.text = question.choices[0]
            quizChoice2.text = question.choices[1]
            quizChoice3.text = question.choices[2]
            quizChoice4.text = question.choices[3]
            quizChoices.clearCheck()
            btnNext.isEnabled = false
            quizProgress.progress = questionNumber
            quizProgressText.text = String.format(
                getString(R.string.quiz_progress),
                questionNumber,
                questionData.size
            )
        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val view = DialogQuizBinding.inflate(layoutInflater)
        view.infoTitle.text = getString(R.string.exit_quiz_title)
        view.infoDescription.text = getString(R.string.exit_quiz_description)
        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        view.btnCancel.setOnClickListener { alertDialog.cancel() }
        view.btnConfirm.setOnClickListener {
            alertDialog.cancel()
            super.onBackPressed()
        }
    }
}