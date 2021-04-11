package hu.dmorvai.myseries.ui.filter

import android.content.Context
import androidx.fragment.app.Fragment
import hu.dmorvai.myseries.MySeriesApplication.Companion.injector
import javax.inject.Inject

class FilterFragment : Fragment(), FilterScreen {

    @Inject
    lateinit var filterPresenter: FilterPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun show() {
        TODO()
    }
}