package com.interview.interviewtest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
data class PostEntity(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "brand")
    var brand: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "price")
var price: String = "",
    @ColumnInfo(name = "product_link")
var product_link: String ="",

    @ColumnInfo(name = "image_link")
var image_link: String =""

)