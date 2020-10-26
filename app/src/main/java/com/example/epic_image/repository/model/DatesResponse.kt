package com.example.epic_image.repository.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class DatesResponse : ArrayList<DatesResponseItem>()

@Entity(tableName = "dates_table")
data class DatesResponseItem(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @field:SerializedName("date")
    val date: String? = null
):Serializable