package com.cookie.note.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.cookie.note.data.local.entities.UserRecord

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(userRecord: UserRecord)
}