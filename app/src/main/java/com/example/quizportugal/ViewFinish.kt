package com.example.quizportugal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ViewFinish : AppCompatActivity() {

    private lateinit var mainModel: IModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_finish)
        mainModel = intent.getSerializableExtra("mainModel") as IModel

        var buttonFinish = findViewById<Button>(R.id.buttonReturn)
        buttonFinish.setOnClickListener{
            mainModel.screenTransition(this,this)
        }
    }
}