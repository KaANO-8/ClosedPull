package com.kaano8.closedpull.util

import java.text.ParseException
import java.text.SimpleDateFormat

object DateFormatter {

    fun formatDate(
        inputDate: String,
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
        outputFormat: String = "dd-MMM-yyyy"
    ): String {
        val inputFormatter = SimpleDateFormat(inputFormat)
        try {
            val date = inputFormatter.parse(inputDate.dropLast(1))
            return SimpleDateFormat(outputFormat).format(date)
        } catch (exception: ParseException) {
            println(exception.message)
        }
        return inputDate
    }
}