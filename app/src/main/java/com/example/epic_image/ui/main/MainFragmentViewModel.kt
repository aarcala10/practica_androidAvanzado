package com.example.epic_image.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.epic_image.repository.db.EpicRoomDatabase
import com.example.epic_image.repository.model.EpicResponseItem

class MainFragmentViewModel(private val context: Application) : ViewModel() {

    fun getLocalAllEpic(): LiveData<List<EpicResponseItem>> {

        return EpicRoomDatabase.getInstance(context).epicDao().getAllEpic()
    }
}