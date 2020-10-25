package com.example.epic_image.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.epic_image.repository.model.EpicResponseItem

@Dao
abstract class EpicDao {

    @Query("SELECT * FROM epics_table")
    abstract fun getAllEpic(): LiveData<List<EpicResponseItem>>

    @Query("SELECT * FROM epics_table WHERE date = :epicDate")
    abstract fun getEpicsDate(epicDate: String): LiveData<List<EpicResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEpic(epicsResponseItem: EpicResponseItem)

    @Delete
    abstract fun deleteApod(epicsResponseItem: EpicResponseItem)
}