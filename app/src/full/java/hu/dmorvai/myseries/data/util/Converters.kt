package hu.dmorvai.myseries.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun jsonToList(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return if (value == null || value == "null") {
            emptyList()
        } else Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToJson(list: List<String>?): String {
        return Gson().toJson(list)
    }
}