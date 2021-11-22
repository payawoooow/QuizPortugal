package com.example.quizportugal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.quizportugal.IDAO
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

const val DB_NAME_ASSET = "wordDB.db"
const val DB_VERSION: Int = 1

class DBHelper(private val context: Context, databaseName: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, databaseName, factory, version) {

    private val databasePath: File = context.getDatabasePath(databaseName)
 
    override fun onCreate(database: SQLiteDatabase?) {
        /**database?.execSQL("CREATE TABLE IF NOT EXISTS words("  + 
        "word_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "portugal VARCHAR(100) NOT NULL," +
        "japanese VARCHAR(100) NOT NULL" +
        ");")*/
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //if (oldVersion < newVersion) {
            //database?.execSQL("alter table SampleTable add column deleteFlag integer default 0")
        //}
    }

    /**
     * 最新のデータベースファイルが存在するか確認する
     * 存在すればTrue　しなければFalse
     */
    private fun isLatestDBExists(): Boolean {
        val dbPath = databasePath.absolutePath
        var targetDB: SQLiteDatabase? = null

        try {
            targetDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY) ?: null
            if (targetDB == null) {
                return false
            }

            val oldVersion: Int = targetDB.version
            val newVersion: Int = DB_VERSION

            if (newVersion == oldVersion) {
                return true
            }

            val file = File(dbPath)
            file.delete()

        } catch (e: Exception) {
            Log.e("isDatabaseExists", e.toString())
        } finally {
            targetDB?.close()
        }

        return false
    }

    /**
     * データベースが初期化されていなければ初期化をする
     */
    public fun tryInitializeDatabase() {
        if (databasePath.exists() && isLatestDBExists()) {
            return 
        }

        var db: SQLiteDatabase? = null
        var targetDB: SQLiteDatabase? = null

        try {
            db = this.readableDatabase
            copyDatabaseFromAssets()

            val dbPath = databasePath.absolutePath
            targetDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE) ?: null
            targetDB?.version = DB_VERSION
        } catch (e: Exception) {
            Log.e("tryInitializeDatabase", e.toString())
        } finally {
            targetDB?.close()
            db?.close()
        }
    }

    /**
     * データベースファイルをAssetsフォルダからAndroidのデータベースフォルダにコピーする
     */
    private fun copyDatabaseFromAssets() {
        val input: InputStream = context.assets.open(DB_NAME_ASSET)
        val output = FileOutputStream(databasePath.absolutePath)

        val buffer: ByteArray = ByteArray(1024)
        var size: Int
        while (true) {
            size = input.read(buffer)
            if (size <= 0) break
            output.write(buffer, 0, size)
        }

        output.flush()
        output.close()
        input.close()
    }



}