package com.demoapp.data.pojo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class UserEntity(
    @SerializedName("gender")
    var gender: String="",
    @SerializedName("name")
    var name: NameEntity?=null,
    @SerializedName("location")
    var location: LocationEntity?=null,
    @PrimaryKey
    @SerializedName("email")
    var email: String="",
    @SerializedName("dob")
    var dob: DobEntity?=null,
    @SerializedName("phone")
    var phone: String="",
    @SerializedName("cell")
    var cell: String="",
    @Ignore
    @SerializedName("id")
    var id: IdEntity?=null,
    @SerializedName("picture")
    var picture: PictureEntity?=null
){

   fun getDisplayImage() = picture?.large?:""

   fun getDisplayName() = "${name?.title?:""} ${name?.first?:""} ${name?.last?:""}"

   fun getDisplayDetails() = "$email"

}

data class PictureEntity(
    @SerializedName("large")
    val large: String = "",
    @SerializedName("medium")
    val medium: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = ""
)

data class IdEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)


data class DobEntity(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("age")
    var age: Int = 0
)


data class LocationEntity(
    @SerializedName("street")
    var street: StreetEntity? = null,
    @SerializedName("city")
    val city: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("postcode")
    val postcode: String = ""
)


data class StreetEntity(
    @SerializedName("number")
    var number: Int,
    @SerializedName("name")
    val name: String
)

data class NameEntity(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("first")
    val first: String = "",
    @SerializedName("last")
    val last: String = ""
)
