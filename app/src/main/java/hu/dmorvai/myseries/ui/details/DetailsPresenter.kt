package hu.dmorvai.myseries.ui.details

import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.interactor.series.event.GetSeasonsEvent
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import hu.dmorvai.myseries.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val executor: Executor,
    private val seriesInteractor: SeriesInteractor
) : Presenter<DetailsScreen>() {

    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun querySeasons(serie: Serie) {
        executor.execute {
            seriesInteractor.getSeasons(serie.id!!)
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetSeasonsEvent) {
        if (event.throwable != null) {
            println("Error")
        } else {
            screen?.showDetails(event.seasons as MutableList<Season>)
        }
    }
}