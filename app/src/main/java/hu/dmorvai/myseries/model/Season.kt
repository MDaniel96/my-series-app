package hu.dmorvai.myseries.model

import com.google.gson.annotations.SerializedName

data class Season(

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("number")
    var number: Int? = null,

    @SerializedName("premiereDate")
    var premiereDate: String? = null
)