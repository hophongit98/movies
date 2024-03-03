package com.example.egsassignment.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
object DateFormatUtils {

    const val MMDDYYYY = "MM/dd/yyyy"

    private fun getCalendar() = Calendar.getInstance()

    private fun getTimeZoneSimpleDateFormatter(format: String): SimpleDateFormat {
        return SimpleDateFormat(format, Locale.getDefault())
    }

    /**
     * @param dateString: yyyy-MM-dd
     * @return the year of the input String. Otherwise return null if the input is in a different format.
     */
    fun getYear(dateString: String): Int? {
        val formatter = getTimeZoneSimpleDateFormatter("yyyy-MM-dd")

        return try {
            val date = formatter.parse(dateString)

            val calendar = Calendar.getInstance()
            calendar.time = date!!
            calendar.get(Calendar.YEAR)
        } catch (e: Exception) {
            print("Phillip - Error parsing date: ${e.message}")
            null
        }
    }

    /**
     * @param dateString: yyyy-MM-dd
     * @return the year of the input String. Otherwise return null if the input is in a different format.
     */
    fun convertTo(dateString: String, format: String): String? {
        val inputFormatter = getTimeZoneSimpleDateFormatter("yyyy-MM-dd")
        val outputFormatter = getTimeZoneSimpleDateFormatter(format)

        return try {
            return outputFormatter.format(inputFormatter.parse(dateString)!!)
        } catch (e: Exception) {
            print("Phillip - Error parsing date: ${e.message}")
            null
        }
    }
}