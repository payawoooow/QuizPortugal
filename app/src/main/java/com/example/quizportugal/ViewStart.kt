package com.example.quizportugal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ViewStart : AppCompatActivity() {
    private lateinit var mainModel: IModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_start)
        mainModel = intent.getSerializableExtra("mainModel") as IModel

        var buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.setOnClickListener{
            mainModel.screenTransition(this,this)
        }
    }
}