package hu.dmorvai.myseries.ui.series

import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.interactor.series.event.GetSeasonsEvent
import hu.dmorvai.myseries.interactor.series.event.GetSeriesEvent
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import hu.dmorvai.myseries.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class SeriesPresenter @Inject constructor(
    private val executor: Executor,
    private val seriesInteractor: SeriesInteractor
) : Presenter<SeriesScreen>() {

    override fun attachScreen(screen: SeriesScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    suspend fun addToFavouriteSeries(serie: Serie) {
        seriesInteractor.saveFavouriteSerie(serie)
    }

    suspend fun queryFavouriteSeries(): List<Serie> {
        return seriesInteractor.getFavouriteSeries()
    }

    suspend fun deleteFavouriteSerie(serie: Serie) {
        seriesInteractor.deleteFavouriteSerie(serie)
    }

    fun querySeries(title: String) {
        executor.execute {
            seriesInteractor.getSeriesByTitle(title)
        }
    }

    fun querySeasons(serieId: Long) {
        executor.execute {
            seriesInteractor.getSeasons(serieId)
        }
    }

    fun applyFilter(categoryFilters: List<String>) {
        screen?.showFilteredSeries(categoryFilters)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetSeriesEvent) {
        if (event.throwable != null) {
            println("Error")
        } else {
            screen?.showSeries(event.series as MutableList<Serie>)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetSeasonsEvent) {
        if (event.throwable != null) {
            println("Error")
        } else {
            screen?.showSeasons(event.seasons as MutableList<Season>)
        }
    }
}