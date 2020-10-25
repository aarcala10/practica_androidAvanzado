package com.example.epic_image.ui.dates

import com.example.epic_image.repository.model.DatesResponseItem

interface CallbackDateItemClick {
    fun onItemClick(datesResponseItem: DatesResponseItem)
}