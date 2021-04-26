package hu.dmorvai.myseries.interactor.series.event

import hu.dmorvai.myseries.model.Season

data class GetSeasonsEvent(
    var code: Int = 0,
    var seasons: List<Season>? = null,
    var throwable: Throwable? = null
)
