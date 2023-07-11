package com.example.ostclientapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private lateinit var buttonStudios: Button
    private lateinit var buttonItems: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        buttonItems = findViewById(R.id.itemsListActivityButton)
        buttonItems.setOnClickListener {
            openItemsListActivite()
        }

        buttonStudios = findViewById(R.id.studiosListActivityButton)
        buttonStudios.setOnClickListener {
            openStudiosListActivity()
        }
    }

    private fun openItemsListActivite(){
        val intent = Intent(this, ItemsListActivity::class.java)
        startActivity(intent)
    }

    private fun openStudiosListActivity(){
        val intent = Intent(this, StudiosListActivity::class.java)
        startActivity(intent)
    }

}