package com.example.ostclientapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.ostclientapp.databinding.ActivityItemsListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar


class ItemsListActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private var day = 0
    private var month = 0
    private var year = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0

    private lateinit var datePickerButton: Button
    private lateinit var backButton: Button
    private lateinit var bilding: ActivityItemsListBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bilding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(bilding.root)

        getDayCalendar()

        val textDate = findViewById<TextView>(R.id.date)
        textDate.text = "$day-"+(month+1)+"-$year"

        datePickerButton = findViewById(R.id.datePickerButton)
        datePickerButton.setOnClickListener {
            pickDate()
        }

        datePickerButton = findViewById(R.id.datePickerButton)
        datePickerButton.setOnClickListener {
            pickDate()
        }
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            openStartActivity()
        }

        startActivity(this, "$day-"+ (month+1) +"-$year")
    }

    private fun openStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    private fun startActivity(context: Activity, date: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val url = "https://www.mamont-server.ru:8888/api/check_equipment/$date"
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

    private fun pickDate() {
        getDayCalendar()
        DatePickerDialog(this, this, year, month, day).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        savedDay = day
        savedMonth = month+1
        savedYear = year

        val textDate = findViewById<TextView>(R.id.date)
        textDate.text = "$savedDay-$savedMonth-$savedYear"

        startActivity(this,"$savedDay-$savedMonth-$savedYear")
    }
}