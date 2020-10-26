package com.example.epic_image.repository.network

import com.example.epic_image.repository.model.DatesResponse
import com.example.epic_image.repository.model.EpicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface EpicApi {

    @GET("api/natural/all")
    @Headers("Content-Type: application/json")
    fun getDates(@Query("api_key") apiKey: String): Call<DatesResponse>

    @GET("api/natural/date/{date}")
    @Headers("Content-Type: application/json")
    fun getEpicImages(@Path("date") date: String, @Query("api_key") apiKey: String): Call<EpicsResponse>
}