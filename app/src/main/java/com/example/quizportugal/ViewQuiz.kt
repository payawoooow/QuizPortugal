package com.example.quizportugal

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.isVisible
import com.example.quizportugal.databinding.ActivityViewQuizBinding
import java.lang.Exception
import java.util.*
import kotlin.random.Random

class ViewQuiz : AppCompatActivity() {

    private lateinit var mainModel: IModel
    private lateinit var binding: ActivityViewQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_view_quiz)
        binding = ActivityViewQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGoNext.isVisible = false
        mainModel = intent.getSerializableExtra("mainModel") as IModel
        val dbHelper = DBHelper(this, "WordDB", null, 1)
        val dto = mainModel.questionRequest(DAO(dbHelper))
        binding.textPortugal.text = dto.question

        val incorrectList: Queue<String> = ArrayDeque(listOf(dto.incorrect1, dto.incorrect2, dto.incorrect3))
        val answerButtons: List<Button> = listOf(binding.buttonAnswer1, binding.buttonAnswer2, binding.buttonAnswer3, binding.buttonAnswer4)
        val answerNumber = Random.nextInt(answerButtons.count()) + 1

        for (i in 0 until answerButtons.count()) {
            if (i == answerNumber - 1) {
                answerButtons[i].text = dto.correctAnswer
            } else {
                answerButtons[i].text = incorrectList.poll()
            }
            answerButtons[i].setOnClickListener {
                showAnswerResult(answerNumber, i + 1)
            }
        }

        binding.buttonGoNext.setOnClickListener {
            mainModel.screenTransition(this, this)
        }
    }

    private fun showAnswerResult(answerNumber: Int, answerPlace: Int) {
        if (answerNumber == answerPlace) {
            binding.textResult.text = "正解!!"
        } else {
            binding.textResult.text = "不正解…"
        }
        binding.buttonGoNext.isVisible = true
    }

}