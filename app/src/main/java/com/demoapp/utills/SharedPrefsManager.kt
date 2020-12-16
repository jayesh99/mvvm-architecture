package com.demoapp.utills

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsManager @Inject constructor(
    private val appContext: Application,
    private val aesUtills: AESUtills
) {


    private val PREF_NAME = SharedPreferences::class.java.`package`?.name

    var prefs: SharedPreferences


    init {
        prefs = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    /**
     * Clears all data in SharedPreferences
     */
    fun clearPrefs() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }


    fun removeKey(key: String) {
        prefs.edit().remove(aesUtills.encryptString(key)).apply()
    }

    fun containsKey(key: String): Boolean {
        return prefs.contains(aesUtills.encryptString(key))
    }

    fun getString(key: String): String {
        return prefs.getString(
            aesUtills.encryptString(key),
            ""
        )?.let { aesUtills.decryptString(it) }
            ?: ""
    }

    fun getString(key: Int): String {
        return prefs.getString(
            aesUtills.encryptString(appContext.getString(key)),
            ""
        )?.let { aesUtills.decryptString(it) }
            ?: ""
    }

    fun setString(key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(aesUtills.encryptString(key), aesUtills.encryptString(value))
        editor.apply()
    }

    fun setString(key: Int, value: String) {
        val editor = prefs.edit()
        editor.putString(
            aesUtills.encryptString(appContext.getString(key)),
            aesUtills.encryptString(value)
        )
        editor.apply()
    }

    fun <T> setObject(key: Int, value: T) {
        val editor = prefs.edit()
        editor.putString(
            aesUtills.encryptString(appContext.getString(key)),
            aesUtills.encryptString(Gson().toJson(value))
        )
        editor.apply()
    }

    fun <T> getObject(key: Int, clazz: Class<T>): T {
        return Gson().fromJson(
            prefs.getString(
                aesUtills.encryptString(appContext.getString(key)),
                ""
            )?.let {
                aesUtills.decryptString(
                    it
                )
            }, clazz
        )
    }

    companion object {


        //All pref names
        const val LANGUAGE = "language"
        const val AUTH_TOKEN  = "pref_auth_token"
        const val USER  = "auth_user"

    }

}