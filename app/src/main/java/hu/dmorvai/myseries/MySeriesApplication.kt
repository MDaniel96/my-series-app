package hu.dmorvai.myseries

import android.app.Application
import android.content.Context
import hu.dmorvai.myseries.ui.UIInjectorModule

class MySeriesApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var injector: MySeriesInjectorComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        injector =
            DaggerMySeriesInjectorComponent.builder().uIInjectorModule(UIInjectorModule()).build()
    }
}