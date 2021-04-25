package hu.dmorvai.myseries.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "serie")
data class Serie(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("genres")
    var genres: List<String>? = null,

    @SerializedName("premiered")
    var premiered: String? = null
)