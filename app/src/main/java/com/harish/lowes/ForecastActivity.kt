package com.harish.lowes

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForecastActivity : AppCompatActivity() {

    private var textView_data: TextView? = null
    private var textView_cloud: TextView? = null
    private var textView_temp: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        supportActionBar?.title = intent.getStringExtra("name")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        textView_data = findViewById(R.id.textView_data)
        textView_temp = findViewById(R.id.textView_feegravity)
        textView_cloud = findViewById(R.id.textView_cloud)
        textView_data?.text = intent.getStringExtra("temp")
        textView_temp?.text = "Feels Like : " + intent.getStringExtra("feels_like") + "°F"
        textView_cloud?.text = intent.getStringExtra("cloud") + " : " + intent.getStringExtra("description")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}