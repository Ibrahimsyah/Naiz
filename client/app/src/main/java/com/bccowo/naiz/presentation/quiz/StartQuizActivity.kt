package com.bccowo.naiz.presentation.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.EXTRAS.EXTRA_QUIZ
import com.bccowo.naiz.databinding.ActivityStartQuizBinding
import com.bccowo.naiz.domain.model.Quiz

class StartQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val quiz = intent.getParcelableExtra<Quiz>(EXTRA_QUIZ) as Quiz
        with(binding) {
            quizTitle.text = quiz.title
            quizLevel.text = String.format(getString(R.string.quiz_level), quiz.level)
            quizDescription.text = quiz.description
            btnStartQuiz.setOnClickListener { startQuiz(quiz) }
        }
    }

    private fun startQuiz(quiz: Quiz) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra(EXTRA_QUIZ, quiz)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}