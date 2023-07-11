package com.example.ostclientapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class StudiosListAdapter(private val context: Activity, private val arrayList: ArrayList<StudioListJson>): ArrayAdapter<StudioListJson>(context, R.layout.list_item, arrayList) {

    @SuppressLint("ViewHolder", "InflateParams", "SetTextI18n",)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inFlater = LayoutInflater.from(context)
        val view = inFlater.inflate(R.layout.list_item, null)

        val itemName = view.findViewById<TextView>(R.id.nameItemTV)
        val timeStart = view.findViewById<TextView>(R.id.timeStartTV)
        val timeEnd = view.findViewById<TextView>(R.id.timeEndTV)
        val taskItem = view.findViewById<TextView>(R.id.taskItemTV)
        val dateFormat = DateTimeFormatter.ofPattern("HH:mm")


        itemName.text = arrayList[position].studio_name
        timeStart.text = "С "+
            arrayList[position].dateStart!!.toInstant()
                .atOffset(ZoneOffset.UTC)
                .toLocalDateTime().format(dateFormat)
        timeEnd.text = "До "+
            arrayList[position].dateEnd!!.toInstant()
                .atOffset(ZoneOffset.UTC)
                .toLocalDateTime().format(dateFormat)
        taskItem.text = arrayList[position].task_name


        return view
    }


}