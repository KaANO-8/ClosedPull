package com.kaano8.closedpull.util

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class DateFormatterTest {

    @Test
    fun `test date parsing correctly with default formats`() {
        val inputDate = "2022-06-25T20:58:54Z"
        val outputDate = DateFormatter.formatDate(inputDate)
        assertEquals("25-Jun-2022", outputDate)
    }

    @Test
    fun `test date parsing correctly with custom output format`() {
        val inputDate = "2022-06-25T20:58:54Z"
        val outputDate = DateFormatter.formatDate(inputDate, outputFormat = "dd-MM-yyyy")
        assertEquals("25-06-2022", outputDate)
    }

    @Test
    fun `test date parsing with incorrect format`() {
        val inputDate = "2022-06-25"
        val outputDate = DateFormatter.formatDate(inputDate, inputFormat = "yyyy-MMM-dd")
        assertEquals("2022-06-25", outputDate)
    }
}