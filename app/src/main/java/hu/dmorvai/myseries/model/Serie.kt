package hu.dmorvai.myseries.model

import com.google.gson.annotations.SerializedName

data class Serie(

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("genres")
    var genres: List<String>? = null,

    @SerializedName("premiered")
    var premiered: String? = null
)