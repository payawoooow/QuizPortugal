package com.example.quizportugal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quizportugal.IDAO

const val DB_NAME_ASSET = "WordDB.db"
const val DB_VERSION: Int = 1

class DBHelper(context: Context, databaseName: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, databaseName, factory, version) {

    private val databasePath: File = context.getDatabasePath(databaseName)
 
    override fun onCreate(database: SQLiteDatabase?) {
        /**database?.execSQL("CREATE TABLE IF NOT EXISTS words("  + 
        "word_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "portugal VARCHAR(100) NOT NULL," +
        "japanese VARCHAR(100) NOT NULL" +
        ");")*/
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            //database?.execSQL("alter table SampleTable add column deleteFlag integer default 0")
        }
    }

    private fun isDatabaseExists(): Boolean {
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

    public fun tryInitializeDatabase() {
        if (databasePath.exists() && isDatabaseExists()) {
            return 
        }

        var db: SQLiteDatabase? = null

        try {
            db = this.readbaleDatabase
            copyDatabaseFromAssets()

            val dbPath = databasePath.absolutePath
            val targetDB: SQLiteDatabase? = SQLiteDatabase.OpenDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE) ?: null
            targetDB?.version = DB_VERSION
        } catch (e: Exception) {
            Log.e("tryInitializeDatabase", e.toString())
        } finally {
            targetDB?.close()
            db?.close()
        }
    }

    private fun copyDatabaseFromAssets() {
        val input: InputStream = context.assets.open(DB_NAME_ASSET)
        val output: FileOutputStream = FileOutputStream(databasePath.absolutePath)

        var buffer: ByteArray? = ByteArray(1024)
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