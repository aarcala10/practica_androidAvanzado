package com.example.epic_image.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class EpicsResponse : ArrayList<EpicResponseItem>()

@Entity(tableName = "epics_table")
data class EpicResponseItem(

    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @field:SerializedName("caption")
    val caption: String,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("identifier")
    val identifier: String,

    @field:SerializedName("image")
    val image: String,
):Serializable
