package hu.dmorvai.myseries.ui

import dagger.Module
import dagger.Provides
import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.ui.details.DetailsPresenter
import hu.dmorvai.myseries.ui.filter.FilterPresenter
import hu.dmorvai.myseries.ui.series.SeriesPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule {

    @Provides
    @Singleton
    fun seriesPresenter(executor: Executor, seriesInteractor: SeriesInteractor) = SeriesPresenter(executor, seriesInteractor)

    @Provides
    @Singleton
    fun filterPresenter() = FilterPresenter()

    @Provides
    @Singleton
    fun detailsPresenter() = DetailsPresenter()

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}