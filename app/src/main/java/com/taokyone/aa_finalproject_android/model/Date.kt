package com.taokyone.aa_finalproject_android.model

import java.time.LocalDate
import java.time.LocalTime

data class Date (
    var date: LocalDate,
    var time: LocalTime,
) {
    override fun toString(): String {
        return "Date(date=$date, time=$time)"
    }
}