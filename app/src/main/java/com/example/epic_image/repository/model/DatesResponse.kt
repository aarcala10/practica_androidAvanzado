package com.example.epic_image.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import kotlin.collections.ArrayList

class DatesResponse : ArrayList<DatesResponseItem>()

data class DatesResponseItem(

    val date: String? = null
):Serializable