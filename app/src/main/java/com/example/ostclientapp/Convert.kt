package com.example.ostclientapp

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
    fun convertForItem(arrayList: ArrayList<ItemListJson>): ArrayList<ItemListDate>{
        val result = ArrayList<ItemListDate>()
        val list = ArrayList<String>()
        for(i in 0 until arrayList.size){
            if (!list.contains(arrayList[i].equipment_name))
                list.add(arrayList[i].equipment_name!!)
        }
        for (i in 0 until list.size){
            val buffer = ArrayList<TimesTaskList>()
            for (j in 0 until arrayList.size){
                if (list[i] == arrayList[j].equipment_name){
                    buffer.add(TimesTaskList(arrayList[j].task_name!!, arrayList[j].dateStart!!, arrayList[j].dateEnd!!))
                }
            }
            result.add(ItemListDate(list[i], buffer))
        }
        return result
    }
}