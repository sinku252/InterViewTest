package com.interview.interviewtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.interview.interviewtest.data.PostEntity

@Dao
interface TaskDao
{
    @Insert
    fun savePosts(book: PostEntity)

    @Query(value = "Select * from post_entity")
    fun getAllPosts() : List<PostEntity>


}