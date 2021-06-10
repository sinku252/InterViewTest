package com.interview.interviewtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.interview.interviewtest.data.PostEntity


@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao?
}
