package com.example.epic_image.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epic_image.ui.dates.DatesFragmentViewModel
import com.example.epic_image.ui.epics.EpicsActivity
import com.example.epic_image.ui.epics.EpicsFragmentViewModel
import com.example.epic_image.ui.main.MainFragmentViewModel

class CustomViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainFragmentViewModel::class.java) -> MainFragmentViewModel(application)
                isAssignableFrom(DatesFragmentViewModel::class.java) -> DatesFragmentViewModel(application)
                isAssignableFrom(EpicsFragmentViewModel::class.java) -> EpicsFragmentViewModel(application)
                else -> throw IllegalAccessException("Unknow ViewModel")
            }
        } as T
    }
}