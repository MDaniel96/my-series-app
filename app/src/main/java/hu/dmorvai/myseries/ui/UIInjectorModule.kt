package hu.dmorvai.myseries.ui

import dagger.Module
import dagger.Provides
import hu.dmorvai.myseries.ui.details.DetailsPresenter
import hu.dmorvai.myseries.ui.filter.FilterPresenter
import hu.dmorvai.myseries.ui.series.SeriesPresenter
import javax.inject.Singleton

@Module
class UIInjectorModule {

    @Provides
    @Singleton
    fun seriesPresenter() = SeriesPresenter()

    @Provides
    @Singleton
    fun filterPresenter() = FilterPresenter()

    @Provides
    @Singleton
    fun detailsPresenter() = DetailsPresenter()
}