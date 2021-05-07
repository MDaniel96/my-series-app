package hu.dmorvai.myseries.ui.details

import hu.dmorvai.myseries.model.Season

interface DetailsScreen {
    fun showDetails(seasons: List<Season>)
}