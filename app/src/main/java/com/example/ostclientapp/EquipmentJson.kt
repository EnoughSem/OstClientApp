package com.example.ostclientapp

import com.google.gson.annotations.SerializedName
import java.util.Date

data class EquipmentJson(

    @SerializedName("id"             ) var id            : Int,
    @SerializedName("task_name"      ) var task_name     : String,
    @SerializedName("equipment_name" ) var equipment_name : String,
    @SerializedName("date_start"     ) var dateStart     : Date,
    @SerializedName("date_end"       ) var dateEnd       : Date
)
