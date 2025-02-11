package com.cookie.note.data.mapper

import com.cookie.note.data.local.entities.NoteRecord
import com.cookie.note.data.remote.dto.note.NoteResponse
import com.cookie.note.domain.models.Note
import com.cookie.note.domain.util.isoTimestampToDate
import java.util.Date

// TODO: Learn about Java dates and timezones, epoch time

fun NoteRecord.toNote(): Note{
    val date = Date(lastUpdatedAt)
    return Note(
        title = title,
        localId = localId,
        content = content,
        lastUpdatedAt = date
    )
}

fun NoteResponse.toNoteRecord(): NoteRecord{
    return NoteRecord(
        id = id,
        userId = userId,
        title = title,
        content = content,
        createdAt = isoTimestampToDate(createdAt).time,
        lastUpdatedAt = isoTimestampToDate(lastUpdatedAt).time,
        latitude = latitude,
        longitude = longitude,
        address = address
    )
}