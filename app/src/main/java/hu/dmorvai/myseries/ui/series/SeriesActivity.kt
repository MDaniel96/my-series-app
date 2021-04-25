package hu.dmorvai.myseries.ui.series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import javax.inject.Inject

class SeriesActivity : AppCompatActivity(), SeriesScreen {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
        injector.inject(this)
        seriesPresenter.attachScreen(this)
    }

    override fun onDestroy() {
        seriesPresenter.detachScreen()
        super.onDestroy()
    }

    override fun showSeries(series: List<Serie>) {
        TODO()
    }

    override fun showSeasons(seasons: List<Season>) {
        TODO()
    }
}