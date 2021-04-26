package hu.dmorvai.myseries.interactor.series.event

import hu.dmorvai.myseries.model.Serie

data class GetSeriesEvent(
    var code: Int = 0,
    var series: List<Serie>? = null,
    var throwable: Throwable? = null
)
