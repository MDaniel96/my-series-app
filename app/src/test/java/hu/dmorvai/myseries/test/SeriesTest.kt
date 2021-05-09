package hu.dmorvai.myseries.test

import hu.dmorvai.myseries.ui.series.SeriesPresenter
import hu.dmorvai.myseries.ui.series.SeriesScreen
import hu.dmorvai.myseries.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class SeriesTest {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    private lateinit var seriesScreen: SeriesScreen

    @Before
    fun setup() {
//        testInjector.inject(this)
        seriesScreen = mock()
        seriesPresenter.attachScreen(seriesScreen)
    }

//    @Test
//    fun testSeries() {
//        seriesPresenter.querySeries("wheel")
//
//        val list = argumentCaptor<MutableList<Serie>>()
//        Mockito.verify(seriesScreen).showSeries(list.capture())
//        assert(list.value.size > 0)
//    }

    @After
    fun tearDown() {
        seriesPresenter.detachScreen()
    }
}