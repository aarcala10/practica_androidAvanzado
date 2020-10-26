package com.example.epic_image.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.epic_image.repository.model.DatesResponseItem

@Dao
abstract class EpicDao {

    @Query("SELECT * FROM dates_table")
    abstract fun getAllDate(): LiveData<List<DatesResponseItem>>

    @Query("SELECT * FROM dates_table WHERE date = :date")
    abstract fun getDate(date: String): LiveData<List<DatesResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDate(datesResponseItem: DatesResponseItem)

    @Delete
    abstract fun deleteDate(datesResponseItem: DatesResponseItem)
}