package com.example.quizportugal

interface IModel{
    //INPUT：なし　OUTPUT：問題(DAO)
    fun questionRequest(): DAO
    //INPUT：自分自身 OUTPUT：なし
    fun screenTransition(): Unit
}


