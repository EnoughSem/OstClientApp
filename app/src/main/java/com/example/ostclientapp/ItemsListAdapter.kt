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

class ItemsListAdapter(private val context: Activity, private val arrayList: ArrayList<ItemListDate>): ArrayAdapter<ItemListDate>(context, R.layout.list_item, arrayList) {

    @SuppressLint("ViewHolder", "InflateParams", "SetTextI18n",)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inFlater = LayoutInflater.from(context)
        val view = inFlater.inflate(R.layout.list_item, null)

        val itemName = view.findViewById<TextView>(R.id.nameItemTV)
        val text = view.findViewById<TextView>(R.id.textTV)

        itemName.text = arrayList[position].name
        text.text = arrayList[position].arrayList.toString()


        return view
    }


}