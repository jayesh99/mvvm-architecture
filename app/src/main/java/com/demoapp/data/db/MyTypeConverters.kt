package com.demoapp.data.db

import android.text.TextUtils
import androidx.room.TypeConverter
import com.demoapp.data.pojo.DobEntity
import com.demoapp.data.pojo.LocationEntity
import com.demoapp.data.pojo.NameEntity
import com.demoapp.data.pojo.PictureEntity
import com.google.gson.Gson

object MyTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringTOUserProfile(nameString: String): NameEntity {
        if (TextUtils.isEmpty(nameString))
            return NameEntity()
        else
            return Gson().fromJson(nameString, NameEntity::class.java)
    }


    @TypeConverter
    @JvmStatic
    fun userProfileToString(name: NameEntity?): String {
        return if (name == null)
            ""
        else
            Gson().toJson(name)
    }

    @TypeConverter
    @JvmStatic
    fun stringTOLocationEntity(string: String): LocationEntity {
        if (TextUtils.isEmpty(string))
            return LocationEntity()
        else
            return Gson().fromJson(string, LocationEntity::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun locationEntityToString(data: LocationEntity?): String {
        return if (data == null)
            ""
        else
            Gson().toJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun stringTODobEntity(string: String): DobEntity {
        if (TextUtils.isEmpty(string))
            return DobEntity()
        else
            return Gson().fromJson(string, DobEntity::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun dobEntityToString(data: DobEntity?): String {
        return if (data == null)
            ""
        else
            Gson().toJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun stringTOPictureEntity(string: String): PictureEntity {
        if (TextUtils.isEmpty(string))
            return PictureEntity()
        else
            return Gson().fromJson(string, PictureEntity::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun pictureEntityToString(data: PictureEntity?): String {
        return if (data == null)
            ""
        else
            Gson().toJson(data)
    }

}