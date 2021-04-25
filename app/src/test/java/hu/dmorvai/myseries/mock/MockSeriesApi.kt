package hu.dmorvai.myseries.mock

import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.SerieResult
import hu.dmorvai.myseries.network.SeriesApi
import retrofit2.Call

class MockSeriesApi : SeriesApi {

    override fun getSeries(title: String): Call<List<SerieResult>> {
        TODO()
    }

    override fun getSeasons(serieId: Long): Call<List<Season>> {
        TODO()
    }
}
