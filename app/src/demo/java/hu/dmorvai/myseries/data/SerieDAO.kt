package hu.dmorvai.myseries.data

import hu.dmorvai.myseries.model.Serie

class SerieDAO {

    private var series = mutableListOf(
        Serie(name = "The Wheel of Time"),
        Serie(name = "A Game of Thrones"),
        Serie(name = "Lord of the Rings")
    )

    fun getSeries() = series

    fun insertSerie(serie: Serie) = series.add(serie)

    fun deleteSerie(serie: Serie) = series.remove(serie)

    fun deleteAllSeries() = series.clear()
}
