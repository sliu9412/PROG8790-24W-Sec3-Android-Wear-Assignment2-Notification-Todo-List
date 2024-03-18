package com.example.siyuliuassignment2.presentation.Store

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalStore(context: Context) {
    private val sharedPreferencesName = "sharedPreference"
    private val sharedPreferencesKey = "sharedPreferenceKey"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveDataList(dataList: List<TaskItem>) {
        editor.putString(sharedPreferencesKey, Json.encodeToString(dataList.toList()))
        editor.apply()
    }

    fun loadDataList(): MutableList<TaskItem> {
        val loadedString = sharedPreferences.getString(sharedPreferencesKey, null)
        if (loadedString != null) {
            return Json.decodeFromString<MutableList<TaskItem>>(loadedString)
        }
        return mutableListOf<TaskItem>()
    }
}