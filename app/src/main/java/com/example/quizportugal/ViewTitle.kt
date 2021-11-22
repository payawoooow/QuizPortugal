package com.example.quizportugal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class ViewTitle : AppCompatActivity() {

    private lateinit var mainModel: IModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_title)
        //mainModel = intent?.extras?.getPracelable("mainModel")
        mainModel = intent.getSerializableExtra("mainModel") as IModel
        
        this.timerAction()
    }

    private fun timerAction(){
        //kotlinでできる簡単なタイマーイベントの実装
        //第一引数は何ミリセカンド後に実行するか
        //第二引数は何ミリセカンド間隔で実行するかを指定する
        //下記の例は、この記述の実行後10秒後に0ミリセカンド間隔でイベント発生
        //第三引数は処理を記述したラムダ式
        Timer().schedule(10 * 1000, 0) {
            mainModel.screenTransition(this,this);
            //1回で終了
            this.cancel()
        }
    }

}