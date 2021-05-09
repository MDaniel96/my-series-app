package hu.dmorvai.myseries.presenter

import hu.dmorvai.myseries.interactor.series.SeriesInteractor
import hu.dmorvai.myseries.mock.MockSeriesApi
import hu.dmorvai.myseries.ui.series.SeriesPresenter
import hu.dmorvai.myseries.ui.series.SeriesScreen
import hu.dmorvai.myseries.utils.UiExecutor
import hu.dmorvai.myseries.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SeriesPresenterTest {

    lateinit var seriesPresenter: SeriesPresenter

    private val seriesScreen: SeriesScreen = mock()
    private val seriesApi = MockSeriesApi()

    @Before
    fun setup() {
        seriesPresenter = SeriesPresenter(UiExecutor(), SeriesInteractor(seriesApi))
        seriesPresenter.attachScreen(seriesScreen)
    }

    @Test
    fun querySeriesTest() {
        seriesPresenter.querySeries("title")
    }

    @Test
    fun applyFilterTest() {
        seriesPresenter.applyFilter(emptyList())
        Mockito.verify(seriesScreen).showFilteredSeries(emptyList())
    }

    @After
    fun tearDown() {
        seriesPresenter.detachScreen()
    }
}