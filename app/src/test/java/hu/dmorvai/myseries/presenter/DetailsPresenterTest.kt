package hu.dmorvai.myseries.presenter

import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.mock.MockSeriesApi
import hu.dmorvai.myseries.model.Serie
import hu.dmorvai.myseries.ui.details.DetailsPresenter
import hu.dmorvai.myseries.ui.details.DetailsScreen
import hu.dmorvai.myseries.utils.UiExecutor
import hu.dmorvai.myseries.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailsPresenterTest {

    lateinit var detailsPresenter: DetailsPresenter

    private val detailsScreen: DetailsScreen = mock()
    private val seriesApi = MockSeriesApi()

    @Before
    fun setup() {
        detailsPresenter = DetailsPresenter(UiExecutor(), SeriesInteractor(seriesApi))
        detailsPresenter.attachScreen(detailsScreen)
    }

    @Test
    fun querySeasonsTest() {
        detailsPresenter.querySeasons(Serie(id = 1))
    }

    @After
    fun tearDown() {
        detailsPresenter.detachScreen()
    }
}