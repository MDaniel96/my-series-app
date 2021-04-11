package hu.dmorvai.myseries.ui.series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Serie

class SeriesActivity : AppCompatActivity(), SeriesScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
    }

    override fun showSeries(series: List<Serie>) {
        TODO()
    }
}