package com.example.ostclientapp

import com.google.gson.annotations.SerializedName
import java.util.Date

data class StudioListJson(

    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("task_name"      ) var task_name     : String? = null,
    @SerializedName("studio_name" ) var studio_name : String? = null,
    @SerializedName("date_start"     ) var dateStart     : Date? = null,
    @SerializedName("date_end"       ) var dateEnd       : Date? = null

)