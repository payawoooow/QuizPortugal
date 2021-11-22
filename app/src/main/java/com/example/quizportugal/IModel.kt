package com.example.quizportugal

import android.app.Activity
import android.content.Context

interface IModel{
    //INPUT：なし　OUTPUT：問題(DAO)
    fun questionRequest(dao: IDAO): DTO
    //INPUT：自分自身 OUTPUT：なし
    fun screenTransition(activity: Activity, context: Context): Unit
}


