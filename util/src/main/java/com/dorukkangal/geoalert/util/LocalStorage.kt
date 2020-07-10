package com.dorukkangal.geoalert.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(@ApplicationContext context: Context) {

    private val preferences =
        getSharedPreferences(
            context
        )

    var preferencesVersion: Int?
        get() = preferences.get(PreferenceKey.PREFERENCES_VERSION)
        set(value) = preferences.set(PreferenceKey.PREFERENCES_VERSION, value)

    fun clear() {
        preferences.edit(immediately = true) { editor ->
            PreferenceKey.values().forEach {
                if (it.mandatory.not())
                    editor.remove(it.key)
            }
        }
    }

    companion object {
        fun getSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
    }
}

enum class PreferenceKey(val key: String, val mandatory: Boolean = false) {
    PREFERENCES_VERSION("PREFERENCES_VERSION", true),
}

/**
 * Finds value on given key.
 *
 * [T] is the type of value
 *
 * @param key          The name of the preference.
 * @param defaultValue Optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
 * @return The value associated with this key, defValue if there isn't any
 */
inline fun <reified T : Any> SharedPreferences.get(
    key: PreferenceKey,
    defaultValue: T? = null
): T? {
    return when (T::class) {
        String::class -> getString(key.key, defaultValue as? String) as T?
        Int::class -> getInt(key.key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key.key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key.key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key.key, defaultValue as? Long ?: -1) as T?
        Date::class -> getLong(key.key, defaultValue as? Long ?: -1).toDate() as T
        else -> getString(key.key, null)?.let {
            val type = object : TypeToken<T>() {}.type
            Gson().fromJson<T>(it, type)!!
        }
    }
}

/**
 * Puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key].
 *
 * @param key   The name of the preference.
 * @param value The new set for the preference.
 */
fun SharedPreferences.set(key: PreferenceKey, value: Any?, immediately: Boolean = false) {
    when (value) {
        is String? -> edit(immediately) { it.putString(key.key, value) }
        is Int -> edit(immediately) { it.putInt(key.key, value) }
        is Boolean -> edit(immediately) { it.putBoolean(key.key, value) }
        is Float -> edit(immediately) { it.putFloat(key.key, value) }
        is Long -> edit(immediately) { it.putLong(key.key, value) }
        is Date -> edit(immediately) { it.putLong(key.key, value.time) }
        else -> edit(immediately) { it.putString(key.key, Gson().toJson(value)) }
    }
}

@SuppressLint("ApplySharedPref")
private fun SharedPreferences.edit(
    immediately: Boolean = false,
    operation: (SharedPreferences.Editor) -> Unit
) {
    val editor = this.edit()
    operation(editor)

    when (immediately) {
        true -> editor.commit()
        else -> editor.apply()
    }
}
