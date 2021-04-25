package hu.dmorvai.myseries

import dagger.Component
import hu.dmorvai.myseries.interactor.InteractorModule
import hu.dmorvai.myseries.network.NetworkModule
import hu.dmorvai.myseries.ui.UIModule
import hu.dmorvai.myseries.ui.details.DetailsActivity
import hu.dmorvai.myseries.ui.filter.FilterFragment
import hu.dmorvai.myseries.ui.series.SeriesActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface MySeriesInjectorComponent {
    fun inject(seriesActivity: SeriesActivity)
    fun inject(filterFragment: FilterFragment)
    fun inject(detailsActivity: DetailsActivity)
}