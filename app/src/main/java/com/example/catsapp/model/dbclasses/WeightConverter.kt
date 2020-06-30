package com.example.catsapp.model.dbclasses

import androidx.room.TypeConverter
import com.example.catsapp.model.Weight
import com.google.gson.Gson

class WeightConverter {

    private val gson = Gson()

    @TypeConverter
    fun serviceToString(weight: Weight): String{
        return gson.toJson(weight)
    }

    @TypeConverter
    fun stringToService(value: String): Weight{
        return gson.fromJson(value, Weight::class.java)
    }
}