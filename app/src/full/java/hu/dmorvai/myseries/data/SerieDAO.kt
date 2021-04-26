package hu.dmorvai.myseries.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.dmorvai.myseries.model.Serie

@Dao
interface SerieDAO {

    @Query("SELECT * FROM serie")
    suspend fun getSeries(): List<Serie>

    @Insert
    suspend fun insertSerie(serie: Serie)

    @Delete
    suspend fun deleteSerie(serie: Serie)

    @Query("DELETE FROM serie")
    suspend fun deleteAllSeries()
}
