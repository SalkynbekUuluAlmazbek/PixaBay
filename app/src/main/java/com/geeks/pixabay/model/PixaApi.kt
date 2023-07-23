package com.geeks.pixabay.model

import com.geeks.pixabay.PixaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getPictures(
        @Query("q") keyWord: String,
        @Query("key") key: String = "38421040-ba42dfd62f1dd502b340e664a",
        @Query("per_page") perPage: Int = 3,
        @Query("page") Page: Int
    ): Call<PixaModel>

}