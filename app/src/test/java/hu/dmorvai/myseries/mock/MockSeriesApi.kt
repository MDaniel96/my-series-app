package hu.dmorvai.myseries.mock

import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import hu.dmorvai.myseries.model.SerieResult
import hu.dmorvai.myseries.network.SeriesApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

class MockSeriesApi : SeriesApi {

    override fun getSeries(@Query("q") title: String): Call<List<SerieResult>> {
        val serieResults = listOf(
            SerieResult(
                serie = Serie(
                    name = "The Wheel of Time",
                    genres = listOf("Fantasy", "Adventure")
                )
            ),
            SerieResult(
                serie = Serie(
                    name = "Queen's Gambit",
                    genres = listOf("Family")
                )
            ),
            SerieResult(
                serie = Serie(
                    name = "Shadow and Bone",
                    genres = listOf("Fantasy", "Horror")
                )
            )
        )

        return object : Call<List<SerieResult>> {
            @Throws(IOException::class)
            override fun execute(): Response<List<SerieResult>> {
                return Response.success(serieResults)
            }

            override fun enqueue(callback: Callback<List<SerieResult>>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<List<SerieResult>> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }
    }

    override fun getSeasons(@Path("serieId") serieId: Long): Call<List<Season>> {
        val seasons = listOf(
            Season(name = "Season 1"),
            Season(name = "Season 2"),
            Season(name = "Season 3")
        )

        return object : Call<List<Season>> {
            @Throws(IOException::class)
            override fun execute(): Response<List<Season>> {
                return Response.success(seasons)
            }

            override fun enqueue(callback: Callback<List<Season>>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<List<Season>> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }
    }
}
