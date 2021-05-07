package hu.dmorvai.myseries.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.gson.Gson
import hu.dmorvai.myseries.MySeriesApplication.Companion.context
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Season
import hu.dmorvai.myseries.model.Serie
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.list_item_serie.ivImage
import kotlinx.android.synthetic.main.list_item_serie.tvRating
import kotlinx.android.synthetic.main.list_item_serie.tvTitle
import kotlinx.android.synthetic.main.list_item_serie.tvYear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    private lateinit var serie: Serie
    private lateinit var favouriteSeries: List<Serie>

    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        injector.inject(this)
        detailsPresenter.attachScreen(this)
        val serieJson = intent.getStringExtra("SERIE")
        serie = Gson().fromJson(serieJson, Serie::class.java)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = serie.name
        }
        detailsPresenter.querySeasons(serie)
        getFavouriteSeries()
    }

    private fun getFavouriteSeries() {
        lifecycleScope.launch(Dispatchers.Main) {
            favouriteSeries = lifecycleScope.async(Dispatchers.IO) {
                detailsPresenter.queryFavouriteSeries()
            }.await()
            if (favouriteSeries.contains(serie)) {
                menu.getItem(0).icon = ContextCompat.getDrawable(context, R.drawable.ic_star_full)
            }
        }
    }

    override fun onDestroy() {
        detailsPresenter.detachScreen()
        super.onDestroy()
    }

    override fun showDetails(seasons: List<Season>) {
        tvTitle.text = serie.name
        tvYear.text = if (serie.premiered != null) LocalDate.parse(serie.premiered).year.toString() else "TBD"
        tvRating.text = String.format(context.getString(R.string.rating_out_of_10), serie.rating?.average)
        tvGenres.text = serie.genres?.joinToString(", ")
        tvMinutes.text = String.format(context.getString(R.string.minutes), serie.runtime)
        tvSeasons.text = String.format(context.getString(R.string.seasons), seasons.size)
        tvDescription.text = serie.summary
        Glide.with(context).load(serie.image?.medium).into(ivImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_favourite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menuFavourite -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (favouriteSeries.contains(serie)) {
                        detailsPresenter.deleteFavouriteSerie(serie)
                    } else {
                        detailsPresenter.addToFavouriteSeries(serie)
                    }
                }
                val iconId = if (favouriteSeries.contains(serie)) R.drawable.ic_star_outlined else R.drawable.ic_star_full
                menu.getItem(0).icon = ContextCompat.getDrawable(context, iconId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}