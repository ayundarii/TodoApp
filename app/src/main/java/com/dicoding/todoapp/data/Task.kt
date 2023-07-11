package com.dicoding.todoapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 1 : Define a local database table using the schema in app/schema/tasks.json

@Entity("tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo("id")
    val id: Int,

    @NonNull
    @ColumnInfo("title")
    val title: String,

    @NonNull
    @ColumnInfo("description")
    val description: String,

    @NonNull
    @ColumnInfo("dueDate")
    val dueDateMillis: Long,

    @NonNull
    @ColumnInfo("completed")
    val isCompleted: Boolean = false
)
