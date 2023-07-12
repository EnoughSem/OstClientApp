package com.example.ostclientapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date

data class TimesTaskList(var task_name: String, var dateStart: Date, var dateEnd: Date){

    @RequiresApi(Build.VERSION_CODES.O)
    override fun toString(): String {
        val dateFormat = DateTimeFormatter.ofPattern("dd.MM HH:mm")
        return "С "+
                dateStart.toInstant()
                    .atOffset(ZoneOffset.UTC)
                    .toLocalDateTime().format(dateFormat) +"\n" +
                "До " +
                dateEnd.toInstant()
            .atOffset(ZoneOffset.UTC)
            .toLocalDateTime().format(dateFormat) + "\n"+
                task_name + "\n" +"\n"
    }
}
