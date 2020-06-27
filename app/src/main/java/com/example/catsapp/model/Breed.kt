package com.example.catsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breeds_table")
data class Breed (
    @SerializedName("adaptability") var adaptability : Int,
    @SerializedName("affection_level") var affection_level : Int,
    @SerializedName("alt_names") var alt_names : String,
    @SerializedName("cfa_url") var cfa_url : String,
    @SerializedName("child_friendly") var child_friendly : Int,
    @SerializedName("country_code") var country_code : String,
    @SerializedName("country_codes") var country_codes : String,
    @SerializedName("description") var description : String,
    @SerializedName("dog_friendly") var dog_friendly : Int,
    @SerializedName("energy_level") var energy_level : Int,
    @SerializedName("experimental") var experimental : Int,
    @SerializedName("grooming") var grooming : Int,
    @SerializedName("hairless") var hairless : Int,
    @SerializedName("health_issues") var health_issues : Int,
    @SerializedName("hypoallergenic") var hypoallergenic : Int,
    @PrimaryKey @SerializedName("id")@ColumnInfo(name = "breedId") var id : String,
    @SerializedName("indoor") var indoor : Int,
    @SerializedName("intelligence") var intelligence : Int,
    @SerializedName("lap") var lap : Int,
    @SerializedName("life_span") var life_span : String,
    @SerializedName("name") var name : String,
    @SerializedName("natural") var natural : Int,
    @SerializedName("origin") var origin : String,
    @SerializedName("rare") var rare : Int,
    @SerializedName("rex") var rex : Int,
    @SerializedName("shedding_level") var shedding_level : Int,
    @SerializedName("short_legs") var short_legs : Int,
    @SerializedName("social_needs") var social_needs : Int,
    @SerializedName("stranger_friendly") var stranger_friendly : Int,
    @SerializedName("suppressed_tail") var suppressed_tail : Int,
    @SerializedName("temperament") var temperament : String,
    @SerializedName("vcahospitals_url") var vcahospitals_url : String,
    @SerializedName("vetstreet_url") var vetstreet_url : String,
    @SerializedName("vocalisation") var vocalisation : Int,
    //@Ignore @SerializedName("weight") var weight : Weight?,
    @SerializedName("wikipedia_url") var wikipedia_url : String
)