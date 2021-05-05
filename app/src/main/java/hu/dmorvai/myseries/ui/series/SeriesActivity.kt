package hu.dmorvai.myseries.ui.series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.dmorvai.myseries.MySeriesApplication.Companion.context
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import kotlinx.android.synthetic.main.activity_series.*
import javax.inject.Inject

class SeriesActivity : AppCompatActivity(), SeriesScreen {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    private val displayedSeries = mutableListOf<Serie>()

    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
        injector.inject(this)
        seriesPresenter.attachScreen(this)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        seriesAdapter = SeriesAdapter(context, displayedSeries)
        rvSeries.layoutManager = layoutManager
        rvSeries.adapter = seriesAdapter

        btnSearch.setOnClickListener {
            seriesPresenter.querySeries(etSearch.text.toString())
        }
    }

    override fun onDestroy() {
        seriesPresenter.detachScreen()
        super.onDestroy()
    }

    override fun showSeries(series: List<Serie>) {
        displayedSeries.clear()
        displayedSeries.addAll(series)
        seriesAdapter.notifyDataSetChanged()
    }

    override fun showSeasons(seasons: List<Season>) {
        TODO()
    }
}