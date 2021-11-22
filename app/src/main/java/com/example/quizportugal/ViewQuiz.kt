package com.example.quizportugal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewQuiz : AppCompatActivity() {

    private lateinit var mainModel: IModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_view_quiz)
        val binding = ActivityViweQuizBinding
        setContentView(binding.root)
        mainModel = intent.getSerializableExtra("mainModel") as IModel
        val dbHelper = DBHelper(this, "WordDB", null, 1)
        val dto = mainMode.questionRequest(DAO(dbHelper))
        binding.textPortugal.text = dto.question
        val answerNumber = Random.nextInt(4) + 1
        binding.buttonGoNext.visible = false

        when (answerNumber) {
            1 -> {
                binding.buttonAnswer1.text = dto.correctAnswer
                binding.buttonAnswer2.text = dto.incorrect1
                binding.buttonAnswer3.text = dto.incorrect2
                binding.buttonAnswer4.text = dto.incorrect3
            }
            2 -> {
                binding.buttonAnswer1.text = dto.incorrect1
                binding.buttonAnswer2.text = dto.correctAnswer
                binding.buttonAnswer3.text = dto.incorrect2
                binding.buttonAnswer4.text = dto.incorrect3
            }
            3 -> {
                binding.buttonAnswer1.text = dto.incorrect1
                binding.buttonAnswer2.text = dto.incorrect2
                binding.buttonAnswer3.text = dto.correctAnswer
                binding.buttonAnswer4.text = dto.incorrect3
            }
            4 -> {
                binding.buttonAnswer1.text = dto.incorrect1
                binding.buttonAnswer2.text = dto.incorrect2
                binding.buttonAnswer3.text = dto.incorrect3
                binding.buttonAnswer4.text = dto.correctAnswer
            }
            else -> {
                Log.e("ViewQuiz.onCreate", "error")
            }
        }
        
        binding.buttonAnswer1.setOnClickListener {
            if (answerNumber == 1) {
                binding.textResult.text = "正解!!"
            } else {
                binding.textResult.text = "不正解…"
            }
            binding.buttonGoNext.visible = true
        }

        binding.buttonAnswer2.setOnClickListener {
            if (answerNumber == 2) {
                binding.textResult.text = "正解!!"
            } else {
                binding.textResult.text = "不正解…"
            }
 
            binding.buttonGoNext.visible = true
        }

        binding.buttonAnswer3.setOnClickListener {
            if (answerNumber == 3) {
                binding.textResult.text = "正解!!"
            } else {
                binding.textResult.text textResult.text = "不正解…"
            }
            binding.buttonGoNext.visible = true
        }

        binding.buttonAnswer4.setOnClickListener {
            if (answerNumber == 4) {
                binding.textResult.text  = "正解!!"
            } else {
                binding.textResult.text  = "不正解…"
            }
            binding.buttonGoNext.visible = true
        }

        binidng.buttonGoNext.setOnClickListener {
            mainModel.screenTransition(this, this)
        }

    }


}