package com.interview.interviewtest.db

import androidx.room.Insert
import androidx.room.Query
import com.interview.interviewtest.data.PostEntity

interface TaskDao
{
    @Insert
    fun savePosts(book: PostEntity)

    @Query(value = "Select * from PostEntity")
    fun getAllPosts() : List<PostEntity>
}