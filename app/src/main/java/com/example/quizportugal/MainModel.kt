package com.example.quizportugal


class MainModel() : IModel {

    var quizCount: Int = 0
    
    //コンストラクタ作る

    override fun questionRequest(dao: IDAO): DTO {
        return dao.questionRequest()
    }

    //各View側で渡したMainModelを受け取る処理
    override fun screenTransition(activity: Activity, context: Context): Unit {

        var intent: Intent?

        when (activity) {
            is ViewTitle -> {
                intent = Intent(context, ViewStart::class.java)
            }

            is ViewStart -> {
                intent = Intent(context, ViewQuiz::class.java)
            }

            is ViewQuiz -> {
                if (quizCount > 10) {
                    intent = Intent(context, ViewFinish::class.java)
                }

                quizCount++
                intent = Intent(context, ViewQuiz::class.java)
            }

            is ViewFinish -> {
                intent = Intnet(context, ViewStart::class.java)
                quizCount = 0
            }
        }

        intent.putExtra("mainModel", this)

        activity.startActivity(intent)
    }


}