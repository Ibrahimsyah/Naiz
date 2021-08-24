package com.bccowo.naiz.presentation.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.Extras.EXTRA_QUIZ
import com.bccowo.naiz.databinding.ActivityQuizBinding
import com.bccowo.naiz.databinding.DialogQuizBinding
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val quizEventViewModel: QuizEventViewModel by viewModel()
    private lateinit var questionData: List<QuizQuestion>
    private lateinit var currentQuestion: QuizQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()

        val quiz = intent.getParcelableExtra<Quiz>(EXTRA_QUIZ) as Quiz
        var questionNumber = 1
        var score = 0
        var partialScore = 0
        quizEventViewModel.getQuestions(quiz.id).observe(this, { it ->
            questionData = it
            binding.quizLevel.text = String.format(getString(R.string.quiz_level), quiz.level)
            binding.quizProgress.max = it.size
            partialScore = 100 / it.size
            renderQuestion(questionNumber++)
        })

        binding.quizChoices.setOnCheckedChangeListener { _, checkedId ->
            binding.btnNext.isEnabled = true
        }
        binding.btnNext.setOnClickListener {
            if (questionNumber <= questionData.size) {
                val answer = when (binding.quizChoices.checkedRadioButtonId) {
                    binding.choice1.id -> 0
                    binding.choice2.id -> 1
                    binding.choice3.id -> 2
                    binding.choice4.id -> 3
                    else -> -1
                }
                if (currentQuestion.choices[answer].isTrue) score += partialScore
                renderQuestion(questionNumber++)
            } else {
                val intent = Intent(this, QuizResultActivity::class.java)
                intent.putExtra(QuizResultActivity.EXTRA_SCORE, score)
                intent.putExtra(EXTRA_QUIZ, quiz)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun renderQuestion(questionNumber: Int) {
        val question = questionData[questionNumber - 1]
        currentQuestion = question
        with(binding) {
            quizChoices.clearCheck()
            quizQuestion.text = question.question
            if (question.image != null) {
                quizImageHolder.visibility = View.VISIBLE
                quizImage.load(question.image)
            } else quizImageHolder.visibility = View.GONE
            choice1.text = question.choices[0].option
            choice2.text = question.choices[1].option
            choice3.text = question.choices[2].option
            choice4.text = question.choices[3].option
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