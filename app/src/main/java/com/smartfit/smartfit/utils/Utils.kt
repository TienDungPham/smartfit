package com.smartfit.smartfit.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun parseDate(date: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return try {
            format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getGenders(): List<String> {
        return listOf("Male", "Female")
    }

    fun getGoals(): List<String> {
        return listOf(
            "Maintain Weight",
            "Mile Weight Loss 0.25 kg/week",
            "Weight Loss 0.5 kg/week",
            "Extreme Weight Loss 1 kg/week"
        )
    }
}