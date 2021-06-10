package com.interview.interviewtest.data


import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("api_featured_image")
    var apiFeaturedImage: String="",
    @SerializedName("brand")
    var brand: String="",
    @SerializedName("category")
    var category: String,
    @SerializedName("created_at")
    var createdAt: String="",
    @SerializedName("currency")
    var currency: String="",
    @SerializedName("description")
    var description: String="",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("image_link")
    var imageLink: String="",
    @SerializedName("name")
    var name: String="",
    @SerializedName("price")
    var price: String="",
    @SerializedName("price_sign")
    var priceSign: String="",
    @SerializedName("product_api_url")
    var productApiUrl: String="",
    @SerializedName("product_colors")
    var productColors: List<Any>,
    @SerializedName("product_link")
    var productLink: String="",
    @SerializedName("product_type")
    var productType: String="",
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("tag_list")
    var tagList: List<String>,
    @SerializedName("updated_at")
    var updatedAt: String="",
    @SerializedName("website_link")
    var websiteLink: String=""
)
