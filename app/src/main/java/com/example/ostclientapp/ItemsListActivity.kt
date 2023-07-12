package com.example.ostclientapp

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.ostclientapp.databinding.ActivityItemsListBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class ItemsListActivity : AppCompatActivity() {

    private var day = 0
    private var month = 0
    private var year = 0

    private lateinit var datePickerButton: Button
    private lateinit var backButton: Button
    private lateinit var bilding: ActivityItemsListBinding
    private lateinit var textDate: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bilding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(bilding.root)

        getDayCalendar()

        textDate = findViewById(R.id.date)
        textDate.text = "$day-"+(month+1)+"-$year"

        datePickerButton = findViewById(R.id.datePickerButton)
        datePickerButton.setOnClickListener {
            showDateRangePicker()
        }
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            openStartActivity()
        }

        addList(this, "$day-"+ (month+1) +"-$year","$day-"+ (month+1) +"-$year")
    }

    private fun openStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    private fun addList(context: Activity, dateStart: String, dateEnd: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val url = "https://www.mamont-server.ru:8888/api/check_equipment/$dateStart/$dateEnd"
            HttpClient().use { client ->
                val dateString = client.get<String>(url)
                val typeToken = object : TypeToken<ArrayList<ItemListJson>>() {}.type
                val itemsList = Gson().fromJson<ArrayList<ItemListJson>>(dateString, typeToken)
                CoroutineScope(Dispatchers.Main).launch {
                    bilding.listview.isClickable = true
                    bilding.listview.adapter = ItemsListAdapter(context, itemsList)
                }
            }
        }
    }

    private fun getDayCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun showDateRangePicker() {

        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Выберите дату")
            .build()

        dateRangePicker.show(supportFragmentManager, "date_range_pucker")

        dateRangePicker.addOnPositiveButtonClickListener{ datePicker ->

            val startDate = convertLongToDate(datePicker.first)
            val secondDate = convertLongToDate(datePicker.second)

            textDate = findViewById(R.id.date)
            textDate.text = "$startDate - $secondDate"

            addList(this, startDate, secondDate)
        }
    }

    private fun convertLongToDate(time: Long): String{

        val date = Date(time)
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(date)


    }
}