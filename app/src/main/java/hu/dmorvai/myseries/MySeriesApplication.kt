package hu.dmorvai.myseries

import android.app.Application
import android.content.Context
import hu.dmorvai.myseries.data.AppDatabase
import hu.dmorvai.myseries.ui.UIModule

class MySeriesApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var injector: MySeriesInjectorComponent
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        injector = DaggerMySeriesInjectorComponent.builder().uIModule(UIModule()).build()
        appDatabase = AppDatabase.getInstance(context)
    }
}