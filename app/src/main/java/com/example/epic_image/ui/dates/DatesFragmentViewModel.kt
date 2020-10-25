package com.example.epic_image.ui.dates

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.epic_image.repository.model.DatesResponse
import com.example.epic_image.repository.network.EpicService
import com.example.epic_image.utils.ApiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatesFragmentViewModel(private val context: Application) : ViewModel() {


    fun getDates(cb: EpicService.CallbackResponse<DatesResponse>) {
        EpicService().epicApi.getDates(ApiKey.API_KEY).enqueue(object : Callback<DatesResponse> {
            override fun onResponse(call: Call<DatesResponse>, response: Response<DatesResponse>) {

                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                    
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<DatesResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }


}