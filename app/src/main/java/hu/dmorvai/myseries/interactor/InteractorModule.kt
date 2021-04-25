package hu.dmorvai.myseries.interactor

import dagger.Module
import dagger.Provides
import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.network.SeriesApi
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideSeriesInteractor(seriesApi: SeriesApi) = SeriesInteractor(seriesApi)
}
