package com.example.quizportugal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quizportugal.IDAO


private class DAO(val openHelper:SQLiteOpenHelper) : IDAO {
    
    override fun quetstionRequest(): DTO {         
        var database: SQLiteDatabase? = null 
        val sql = "SELECT word_id, portugal, japanese FROM words ORDER BY RAND() LIMIT 4;"

        try {
            database = openHelper.readableDatabase
            val cursor = database?.rawQuery(sql, null)

            var dto: DTO

            var wordId: Int = 0
            var portugal: String = ""
            val japanese  = mutableListOf<String>()

            cursor.moveToFirst()
            if (cursor.count > 0) {
                wordId = cursor.getInt(0)
                portugal = cursor.getString(1)

                for (int i = 0; i < cursor.getCount(); i++) {
                    japanese.add(cursor.getString(2))
                    c.moveToNext()
                }    
            }
        } catch (e: Exception) {
            Log.e("questionRequest", e.toString())
        } finally {
            database?.close()
        }

        dto = DTO(wordId, portugal, japanese[0] , japanese[1], japanese[2], japanese[3])

        return  dto
    }

}