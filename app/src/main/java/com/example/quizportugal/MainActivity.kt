package com.example.quizportugal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var mainModel: IModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dbHelper = DBHelper(this, "WordDB", null, 1)
        val dbHelper = DBHelper(this, "WordDB", null, 1)
        dbHelper.tryInitializeDatabase()
        mainModel = MainModel()
    }

}