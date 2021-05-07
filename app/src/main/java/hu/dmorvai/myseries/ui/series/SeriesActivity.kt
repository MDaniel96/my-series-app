package hu.dmorvai.myseries.ui.series

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import hu.dmorvai.myseries.MySeriesApplication.Companion.context
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Serie
import hu.dmorvai.myseries.ui.details.DetailsActivity
import hu.dmorvai.myseries.ui.series.filter.FilterSeriesFragment
import kotlinx.android.synthetic.main.activity_series.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeriesActivity : AppCompatActivity(), SeriesScreen, SeriesAdapter.OnItemClickListener {

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
        seriesAdapter = SeriesAdapter(context, displayedSeries, this)
        rvSeries.layoutManager = layoutManager
        rvSeries.adapter = seriesAdapter
        getFavouriteSeries()

        btnSearch.setOnClickListener {
            if (etSearch.text.isNullOrBlank()) {
                getFavouriteSeries()
            } else {
                seriesPresenter.querySeries(etSearch.text.toString())
            }
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

    override fun showFilteredSeries(categoryFilters: List<String>) {
        displayedSeries.removeIf {
            it.genres?.intersect(categoryFilters)?.size == 0
        }
        seriesAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuFilter) {
            val filterSeriesFragment = FilterSeriesFragment()
            filterSeriesFragment.show(supportFragmentManager, "TAG")
        }
        return true
    }

    override fun onItemClicked(serie: Serie) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("SERIE", Gson().toJson(serie))
        startActivity(intent)
    }

    private fun getFavouriteSeries() {
        lifecycleScope.launch(Dispatchers.Main) {
            val favouriteSeries = lifecycleScope.async(Dispatchers.IO) {
                seriesPresenter.queryFavouriteSeries()
            }.await()
            showSeries(favouriteSeries)
        }
    }
}