package com.cookie.note.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cookie.note.data.local.entities.UserRecord

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(userRecord: UserRecord)

    @Query("SELECT * FROM userrecord WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserRecord

    @Query("SELECT idToken FROM userrecord WHERE id = :userId")
    suspend fun getIdTokenForUser(userId: Int): String

    @Query("SELECT username FROM userrecord WHERE id = :userId")
    suspend fun getUsername(userId: Int): String
}