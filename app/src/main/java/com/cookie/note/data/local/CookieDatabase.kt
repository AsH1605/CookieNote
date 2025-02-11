package com.cookie.note.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.dao.UserDao
import com.cookie.note.data.local.entities.NoteRecord
import com.cookie.note.data.local.entities.UserRecord

@Database(
    entities = [NoteRecord::class, UserRecord::class],
    version = 2,
    autoMigrations = [AutoMigration(1, 2)]
)
abstract class CookieDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    abstract fun userDao(): UserDao

    companion object {
        private var dbInstance: CookieDatabase? = null

        fun getCookieDatabase(context: Context): CookieDatabase {
            if (dbInstance != null) {
                return dbInstance!!
            } else {
                synchronized(this) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        CookieDatabase::class.java, "cookie-database"
                    ).build()
                    return dbInstance!!
                }
            }
        }
    }
}