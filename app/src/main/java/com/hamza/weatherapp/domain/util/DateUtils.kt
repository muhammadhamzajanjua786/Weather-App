package com.hamza.weatherapp.domain.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun getDayName(dtTxt: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return try {
            val date = format.parse(dtTxt)
            val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            dayFormat.format(date ?: return "Unknown")
        } catch (e: ParseException) {
            "Unknown"
        }
    }
}