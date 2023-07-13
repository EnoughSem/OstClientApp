package com.example.ostclientapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList
import java.util.Date
data class EquipmentJson(

    @SerializedName("id"             ) var id            : Int,
    @SerializedName("task_name"      ) var task_name     : String,
    @SerializedName("equipment_name" ) var equipment_name : String,
    @SerializedName("date_start"     ) var dateStart     : Date,
    @SerializedName("date_end"       ) var dateEnd       : Date
)

class qweqweqwe(){
    var TaskName = _task_name
    var EquipmentName = _equipment_name
    var TimeStart = _date_start
    var TimeEnd = _date_end
}
class Equipment(_id:Int, _task_name:String, _equipment_name:String, _date_start: Date, _date_end:Date) : Serializable {
    var Id = _id
    var TaskName = _task_name
    var EquipmentName = _equipment_name
    var DateStart = _date_start
    var DateEnd = _date_end
}

class EquipmentStack(_task_name: String, _equipments: ArrayList<EquipmentStackItem>){
    var TaskName = _task_name
    var Equipments = _equipments

    override fun toString(): String {
        return "task: $TaskName, equipments: $Equipments"
    }
}

class EquipmentStackAdapter(var _context: Context, var _res:Int, var _items: ArrayList<EquipmentStack>):
    ArrayAdapter<EquipmentStack>(_context,_res,_items) {
    override fun getItem(position: Int): EquipmentStack {
        return _items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return _items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: LayoutInflater = LayoutInflater.from(_context)
        val view: View = layout.inflate(_res, null)

        val taskName: TextView = view.findViewById(R.id.EquipmentTaskName)
        val taskList: TextView = view.findViewById(R.id.EquipmentTaskItems)

        taskName.text = _items[position].TaskName
        taskList.text = _items[position].Equipments.toString()

        return view
    }
}

class EquipmentStackItem(_equipment_name:String, _date_start: Date, _date_end:Date){
    var EquipmentName = _equipment_name
    var DateStart = _date_start
    var DateEnd = _date_end

    override fun toString(): String {
        return "equipmentName: $EquipmentName, start: $DateStart, end: $DateEnd"
    }
}

class EquipmentStackItemAdapter(var _context: Context, var _res:Int, var _items: ArrayList<EquipmentStackItem>):
    ArrayAdapter<EquipmentStackItem>(_context,_res,_items) {
    override fun getItem(position: Int): EquipmentStackItem {
        return _items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return _items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: LayoutInflater = LayoutInflater.from(_context)
        val view: View = layout.inflate(_res, null)

        val taskName: TextView = view.findViewById(R.id.EquipmentStackItemName)
        val taskStart: TextView = view.findViewById(R.id.EquipmentStackItemStart)
        val taskEnd: TextView = view.findViewById(R.id.EquipmentStackItemEnd)

        taskName.text = _items[position].EquipmentName
        taskStart.text = _items[position].DateStart.toString()
        taskEnd.text = _items[position].DateEnd.toString()

        return view
    }
}