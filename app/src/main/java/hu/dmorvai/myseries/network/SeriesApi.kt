package hu.dmorvai.myseries.network

import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.SerieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("search/shows")
    fun getSeries(@Query("q") title: String): Call<List<SerieResult>>

    @GET("shows/{serieId}/seasons")
    fun getSeasons(@Path("serieId") serieId: Long): Call<List<Season>>
}