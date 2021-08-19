package com.bccowo.naiz.presentation.home.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ItemQuizBinding
import com.bccowo.naiz.domain.model.Quiz

class QuizAdapter(private val listener: (Quiz) -> Unit) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    private val dataList: MutableList<Quiz> = mutableListOf()

    fun setData(dataList: List<Quiz>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class QuizViewHolder(private val view: ItemQuizBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(quiz: Quiz) {
            val icon = getStatusDrawable(quiz.status)
            val background = getBackground(adapterPosition % 4)
            val banner = getBanner(adapterPosition % 4)
            with(view) {
                quizBanner.setImageResource(banner)
                quizWrapper.setBackgroundResource(background)
                quizStatus.setImageResource(icon)
                quizLevel.text =
                    String.format(root.context.getString(R.string.quiz_level), quiz.level)
                quizTitle.text = quiz.title
            }
            itemView.setOnClickListener { listener(quiz) }
        }

        private fun getBanner(index: Int): Int {
            return when (index) {
                0 -> R.drawable.quiz_banner_1
                1 -> R.drawable.quiz_banner_2
                2 -> R.drawable.quiz_banner_3
                3 -> R.drawable.quiz_banner_4
                else -> R.drawable.quiz_banner_1
            }
        }

        private fun getBackground(index: Int): Int {
            return when (index) {
                0 -> R.drawable.bg_quiz_1
                1 -> R.drawable.bg_quiz_2
                2 -> R.drawable.bg_quiz_3
                3 -> R.drawable.bg_quiz_4
                else -> R.drawable.bg_quiz_1
            }
        }

        private fun getStatusDrawable(status: String): Int {
            return when (status) {
                "playable" -> R.drawable.ic_quiz_play
                "locked" -> R.drawable.ic_quiz_lock
                else -> R.drawable.ic_quiz_check
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}