package hu.dmorvai.myseries

import dagger.Component
import hu.dmorvai.myseries.interactor.InteractorModule
import hu.dmorvai.myseries.mock.MockNetworkModule
import hu.dmorvai.myseries.test.SeriesTest
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class])
interface TestComponent : MySeriesInjectorComponent {
    fun inject(seriesTest: SeriesTest)
}
