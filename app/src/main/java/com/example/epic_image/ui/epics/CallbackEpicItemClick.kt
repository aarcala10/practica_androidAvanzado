package com.example.epic_image.ui.epics

import com.example.epic_image.repository.model.EpicResponseItem

interface CallbackEpicItemClick {
    fun onItemClick(epicResponseItem: EpicResponseItem)
}