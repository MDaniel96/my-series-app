package hu.dmorvai.myseries.ui.details

import hu.dmorvai.myseries.model.Serie

interface DetailsScreen {
    fun showDetails(serie: Serie)
}