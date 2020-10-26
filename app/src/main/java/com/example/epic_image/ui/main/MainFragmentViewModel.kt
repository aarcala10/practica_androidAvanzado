package com.example.epic_image.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.epic_image.repository.db.EpicRoomDatabase
import com.example.epic_image.repository.model.DatesResponseItem
import com.example.epic_image.repository.model.EpicResponseItem

class MainFragmentViewModel(private val context: Application) : ViewModel() {

    fun getLocalAllDate(): LiveData<List<DatesResponseItem>> {

        return EpicRoomDatabase.getInstance(context).epicDao().getAllDate()
    }
}