package hu.dmorvai.myseries.interactor.series

import hu.dmorvai.myseries.interactor.series.event.GetSeasonsEvent
import hu.dmorvai.myseries.interactor.series.event.GetSeriesEvent
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.SerieResult
import hu.dmorvai.myseries.network.SeriesApi
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SeriesInteractor @Inject constructor(
    private val seriesApi: SeriesApi
) {

    fun getFavouriteSeries() {
        TODO()
    }

    fun getSeries(title: String) {
        seriesApi.getSeries(title).enqueue(object : Callback<List<SerieResult>> {
            override fun onResponse(call: Call<List<SerieResult>>, response: Response<List<SerieResult>>) {
                EventBus.getDefault().post(
                    GetSeriesEvent(
                        code = response.code(),
                        series = response.body()?.map { it.serie!! }
                    )
                )
            }

            override fun onFailure(call: Call<List<SerieResult>>, t: Throwable) {
                EventBus.getDefault().post(
                    GetSeriesEvent(
                        throwable = t
                    )
                )
            }
        })
    }

    fun getSeasons(serieId: Long) {
        seriesApi.getSeasons(serieId).enqueue(object : Callback<List<Season>> {
            override fun onResponse(call: Call<List<Season>>, response: Response<List<Season>>) {
                EventBus.getDefault().post(
                    GetSeasonsEvent(
                        code = response.code(),
                        seasons = response.body()
                    )
                )
            }

            override fun onFailure(call: Call<List<Season>>, t: Throwable) {
                EventBus.getDefault().post(
                    GetSeasonsEvent(
                        throwable = t
                    )
                )
            }
        })
    }
}