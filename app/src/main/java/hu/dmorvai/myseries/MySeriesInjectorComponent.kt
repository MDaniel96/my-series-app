package hu.dmorvai.myseries

import dagger.Component
import hu.dmorvai.myseries.ui.UIInjectorModule
import hu.dmorvai.myseries.ui.details.DetailsActivity
import hu.dmorvai.myseries.ui.filter.FilterFragment
import hu.dmorvai.myseries.ui.series.SeriesActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIInjectorModule::class])
interface MySeriesInjectorComponent {
    fun inject(seriesActivity: SeriesActivity)
    fun inject(filterFragment: FilterFragment)
    fun inject(detailsActivity: DetailsActivity)
}