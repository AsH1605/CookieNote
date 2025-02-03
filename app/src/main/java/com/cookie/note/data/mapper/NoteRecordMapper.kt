package com.cookie.note.data.mapper

import com.cookie.note.data.local.entities.NoteRecord
import com.cookie.note.domain.models.Note
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