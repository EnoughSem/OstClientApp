package com.example.ostclientapp

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ItemListJson(

    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("task_name"      ) var task_name     : String? = null,
    @SerializedName("equipment_name" ) var equipment_name : String? = null,
    @SerializedName("date_start"     ) var dateStart     : Date? = null,
    @SerializedName("date_end"       ) var dateEnd       : Date? = null

)
