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

    lateinit var serie: Serie

    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun querySeasons(serie: Serie) {
        this.serie = serie
        executor.execute {
            seriesInteractor.getSeasons(serie.id!!)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetSeasonsEvent) {
        if (event.throwable != null) {
            println("Error")
        } else {
            screen?.showDetails(serie, event.seasons as MutableList<Season>)
        }
    }

    fun markFavourite() {
        TODO()
    }
}