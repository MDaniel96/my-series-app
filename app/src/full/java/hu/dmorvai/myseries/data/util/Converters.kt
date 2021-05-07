package hu.dmorvai.myseries.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.dmorvai.myseries.model.Image
import hu.dmorvai.myseries.model.Rating

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

    @TypeConverter
    fun fromRating(rating: Rating?): String? {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().toJson(rating, type)
    }

    @TypeConverter
    fun toRating(ratingString: String?): Rating? {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().fromJson<Rating>(ratingString, type)
    }

    @TypeConverter
    fun fromImage(image: Image?): String? {
        val type = object : TypeToken<Image>() {}.type
        return Gson().toJson(image, type)
    }

    @TypeConverter
    fun toImage(imageString: String?): Image? {
        val type = object : TypeToken<Image>() {}.type
        return Gson().fromJson<Image>(imageString, type)
    }
}