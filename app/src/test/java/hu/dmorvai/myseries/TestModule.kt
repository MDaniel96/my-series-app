package hu.dmorvai.myseries

import dagger.Module
import dagger.Provides
import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.ui.details.DetailsPresenter
import hu.dmorvai.myseries.ui.series.SeriesPresenter
import hu.dmorvai.myseries.utils.UiExecutor
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    @Singleton
    fun provideSeriesPresenter(executor: Executor, seriesInteractor: SeriesInteractor) =
        SeriesPresenter(executor, seriesInteractor)

    @Provides
    @Singleton
    fun detailsPresenter(executor: Executor, seriesInteractor: SeriesInteractor) = DetailsPresenter(executor, seriesInteractor)


    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()
}
