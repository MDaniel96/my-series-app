package hu.dmorvai.myseries.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
import java.time.LocalDate
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        injector.inject(this)
        detailsPresenter.attachScreen(this)
        val serieJson = intent.getStringExtra("SERIE")
        val serie = Gson().fromJson(serieJson, Serie::class.java)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = serie.name
        }
        detailsPresenter.querySeasons(serie)
    }

    override fun onDestroy() {
        detailsPresenter.detachScreen()
        super.onDestroy()
    }

    override fun showDetails(serie: Serie, seasons: List<Season>) {
        tvTitle.text = serie.name
        tvYear.text = if (serie.premiered != null) LocalDate.parse(serie.premiered).year.toString() else "TBD"
        tvRating.text = String.format(context.getString(R.string.rating_out_of_10), serie.rating?.average)
        tvGenres.text = serie.genres?.joinToString(", ")
        tvMinutes.text = String.format(context.getString(R.string.minutes), serie.runtime)
        tvSeasons.text = String.format(context.getString(R.string.seasons), seasons.size)
        tvDescription.text = serie.summary
        Glide.with(context).load(serie.image?.medium).into(ivImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}