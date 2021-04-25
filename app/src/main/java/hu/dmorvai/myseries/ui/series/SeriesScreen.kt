package hu.dmorvai.myseries.ui.series

import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie

interface SeriesScreen {
    fun showSeries(series: List<Serie>)
    fun showSeasons(seasons: List<Season>)
}