package hu.dmorvai.myseries.data

import android.content.Context

class AppDatabase {

    fun serieDao() = SERIE_DAO

    companion object {
        private var INSTANCE = AppDatabase()
        private var SERIE_DAO = SerieDAO()

        fun getInstance(context: Context) = INSTANCE
    }
}