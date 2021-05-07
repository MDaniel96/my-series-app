package hu.dmorvai.myseries.ui.series.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.ui.series.SeriesPresenter
import hu.dmorvai.myseries.ui.utils.setBackgroundTint
import kotlinx.android.synthetic.main.fragment_filter_series.*
import javax.inject.Inject

class FilterSeriesFragment : DialogFragment() {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    private val categoryFilters = mutableListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter_series, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFantasy.setOnClickListener { handleFilterClick(btnFantasy) }
        btnAdventure.setOnClickListener { handleFilterClick(btnAdventure) }
        btnComedy.setOnClickListener { handleFilterClick(btnComedy) }
        btnFamily.setOnClickListener { handleFilterClick(btnFamily) }

        btnCancel.setOnClickListener { dismiss() }

        btnApply.setOnClickListener {
            seriesPresenter.applyFilter(categoryFilters)
            dismiss()
        }
    }

    private fun handleFilterClick(button: Button) {
        button.setBackgroundTint(R.color.teal_200)
        button.setTextColor(resources.getColor(R.color.black))
        categoryFilters.add(button.text.toString())
    }
}