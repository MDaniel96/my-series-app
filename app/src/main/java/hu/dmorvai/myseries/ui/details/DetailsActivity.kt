package hu.dmorvai.myseries.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.model.Serie
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun showDetails(serie: Serie) {
        TODO()
    }
}