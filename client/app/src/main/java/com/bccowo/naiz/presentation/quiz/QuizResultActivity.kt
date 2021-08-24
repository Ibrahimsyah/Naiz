package com.bccowo.naiz.presentation.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.Extras
import com.bccowo.naiz.databinding.ActivityQuizResultBinding
import com.bccowo.naiz.domain.model.Quiz
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SCORE = "EXTRA_SCORE"
    }

    private lateinit var binding: ActivityQuizResultBinding
    private val quizEventViewModel : QuizEventViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quiz = intent.getParcelableExtra<Quiz>(Extras.EXTRA_QUIZ) as Quiz
        val score = intent.getIntExtra(EXTRA_SCORE, 0)
        val desc = if (score >= 60) R.string.quiz_success else R.string.quiz_failed

        if(score >= 60){
            quizEventViewModel.postResult(quiz.id, score)
        }

        lifecycleScope.launch {
            for (i in 0..score) {
                binding.quizScore.text = "$i"
                binding.scoreProgress.progress = i
                delay(50)
            }

            binding.scoreDescription.text = getString(desc)
        }

        binding.btnBackToHome.setOnClickListener {
            finish()
        }
    }
}