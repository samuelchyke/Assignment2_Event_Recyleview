package com.itc.recycleviewroomdatabase.util

import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
object DateUtils {

    private val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
    private val sdf2 = SimpleDateFormat("dd/MM/yyyy")

    fun longToDate(long: Long): Date {
        return Date(long)
    }

    // sdf = Ex: Thu, Jun 9, '22
    fun dateToString(date: Date): String{
        return sdf2.format(date)
    }

}