package com.secondslot.carstest.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.domain.model.Year

@Dao
interface CarDao {

    @Query("SELECT DISTINCT make_id AS id, make AS name FROM cars ORDER BY name")
    suspend fun getMakes(): List<Make>

    @Query("SELECT DISTINCT model AS name FROM cars WHERE make_id == :makeId  ORDER BY name")
    suspend fun getModels(makeId: Int): List<Model>

    @Query("SELECT year FROM cars WHERE make_id = :makeId and model = :model ORDER BY year")
    suspend fun getYears(makeId: Int, model: String): List<Year>
}
