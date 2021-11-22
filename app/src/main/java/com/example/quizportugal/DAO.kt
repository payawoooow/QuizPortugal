package com.example.quizportugal

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.quizportugal.IDAO


public class DAO(private val openHelper:SQLiteOpenHelper) : IDAO {

    override fun questionRequest(): DTO {
        var database: SQLiteDatabase? = null
        var cursor: Cursor? = null
        val sql = "SELECT word_id, portugal, japanese FROM words ORDER BY RAND() LIMIT 4;"
        var wordId: Int = 0
        var portugal: String = ""
        val japanese  = mutableListOf<String>()

        try {
            database = openHelper.readableDatabase
            cursor = database?.rawQuery(sql, null)

            cursor?.moveToFirst()
            if (cursor?.count!! > 0) {
                wordId = cursor.getInt(0)
                portugal = cursor.getString(1)

                for (i in 0 until cursor.count) {
                    japanese.add(cursor.getString(2))
                    cursor.moveToNext()
                }
            }
        } catch (e: Exception) {
            Log.e("questionRequest", e.toString())
        } finally {
            database?.close()
            cursor?.close()
        }

        return DTO(wordId, portugal, japanese[0] , japanese[1], japanese[2], japanese[3])
    }

}