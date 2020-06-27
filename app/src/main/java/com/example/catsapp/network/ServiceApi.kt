package com.example.catsapp.network

import com.example.catsapp.model.Cat
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ServiceApi {

    @Headers("x-api-key: DEMO-API-KEY")
    @GET("search")
    fun getPageAsync(@Query("limit") limit: Int, @Query("page") page: Int, @Query("size") size: String = "med"): Deferred<List<Cat>?>
}