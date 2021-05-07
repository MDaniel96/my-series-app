package hu.dmorvai.myseries.ui.series

import hu.dmorvai.myseries.model.Serie

interface SeriesScreen {
    fun showSeries(series: List<Serie>)
    fun showFilteredSeries(categoryFilters: List<String>)
}