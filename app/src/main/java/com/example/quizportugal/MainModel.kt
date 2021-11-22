package com.example.quizportugal

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable


class MainModel() : IModel, Serializable {

    var quizCount: Int = 0
    
    //コンストラクタ作る

    override fun questionRequest(dao: IDAO): DTO {
        return dao.questionRequest()
    }

    //各View側で渡したMainModelを受け取る処理
    override fun screenTransition(activity: Activity, context: Context): Unit {

        var intent: Intent? = null

        when (activity) {
            is ViewTitle -> {
                intent = Intent(context, ViewStart::class.java)
            }

            is ViewStart -> {
                intent = Intent(context, ViewQuiz::class.java)
            }

            is ViewQuiz -> {
                intent = if (quizCount > 10) {
                    Intent(context, ViewFinish::class.java)
                } else {
                    quizCount++
                    Intent(context, ViewQuiz::class.java)
                }
            }

            is ViewFinish -> {
                intent = Intent(context, ViewStart::class.java)
                quizCount = 0
            }
        }

        intent?.putExtra("mainModel", this)
        activity.startActivity(intent)
    }


}