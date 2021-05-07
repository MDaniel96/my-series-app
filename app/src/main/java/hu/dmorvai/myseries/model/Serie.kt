package hu.dmorvai.myseries.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "serie")
data class Serie(

    @PrimaryKey
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("genres")
    var genres: List<String>? = null,

    @SerializedName("premiered")
    var premiered: String? = null,

    @SerializedName("rating")
    var rating: Rating? = null,

    @SerializedName("image")
    var image: Image? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("summary")
    var summary: String? = null
)