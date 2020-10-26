package com.example.epic_image.ui.epics

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.epic_image.repository.model.DatesResponse
import com.example.epic_image.repository.model.EpicsResponse
import com.example.epic_image.repository.network.EpicService
import com.example.epic_image.utils.ApiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpicsFragmentViewModel(private val context: Application) : ViewModel() {


    fun getEpics(date: String, cb: EpicService.CallbackResponse<EpicsResponse>) {
        EpicService().epicApi.getEpicImages(date,ApiKey.API_KEY).enqueue(object : Callback<EpicsResponse> {

            override fun onResponse(call: Call<EpicsResponse>, response: Response<EpicsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)

                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<EpicsResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }


}