package hu.dmorvai.myseries.ui.series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.dmorvai.myseries.R

class SeriesActivity : AppCompatActivity(), SeriesScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
    }
}