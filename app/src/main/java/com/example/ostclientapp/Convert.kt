package com.example.ostclientapp

import android.util.Log

class Convert {
    fun convertForStudio(arrayList: ArrayList<StudioListJson>): ArrayList<StudioListDate>{
        val result = ArrayList<StudioListDate>()
        val list = ArrayList<String>()
        for(i in 0 until arrayList.size){
            if (!list.contains(arrayList[i].studio_name))
                list.add(arrayList[i].studio_name!!)
        }
        for (i in 0 until list.size){
            val buffer = ArrayList<TimesTaskList>()
            for (j in 0 until arrayList.size){
                if (list[i] == arrayList[j].studio_name){
                    buffer.add(TimesTaskList(arrayList[j].task_name!!, arrayList[j].dateStart!!, arrayList[j].dateEnd!!))
                }
            }
            result.add(StudioListDate(list[i], buffer))
        }
        return result
    }
    fun convertForItem(arrayList: ArrayList<EquipmentJson>): ArrayList<EquipmentStack>{
        val equipmentItems = ArrayList<EquipmentStack>()
        arrayList.forEach { equipmentJson ->
            if (equipmentItems.isNotEmpty()) {
                equipmentItems.forEach { equipmentItem ->
                    if (equipmentItem.TaskName == equipmentJson.task_name) {
                        equipmentItem.Equipments.add(EquipmentStackItem(
                            equipmentJson.equipment_name,
                            equipmentJson.dateStart,
                            equipmentJson.dateEnd
                        ))
                    }
                }
            }else {
                val newList = ArrayList<EquipmentStackItem>()
                newList.add(
                    EquipmentStackItem(
                        equipmentJson.equipment_name,
                        equipmentJson.dateStart,
                        equipmentJson.dateEnd
                    )
                )
                equipmentItems.add(EquipmentStack(equipmentJson.task_name, newList))
            }
        }

        return equipmentItems
    }
}