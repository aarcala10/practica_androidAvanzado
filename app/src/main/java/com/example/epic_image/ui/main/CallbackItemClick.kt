package com.example.epic_image.ui.main

import com.example.epic_image.repository.model.EpicResponseItem

interface CallbackItemClick {
    fun onItemClick(epicResponseItem: EpicResponseItem)
}