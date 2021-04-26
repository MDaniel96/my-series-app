package hu.dmorvai.myseries.model

import com.google.gson.annotations.SerializedName

data class SerieResult(

        @SerializedName("show")
        var serie: Serie? = null
)
