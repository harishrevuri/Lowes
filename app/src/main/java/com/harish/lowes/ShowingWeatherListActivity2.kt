package com.harish.lowes

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harish.lowes.adapter.ForecastAdapter
import com.harish.lowes.model.ForecastModel
import com.harish.lowes.utils.OnItemClickListener
import java.util.*

class ShowingWeatherListActivity : AppCompatActivity(), OnItemClickListener {
    private var forecastModel: ForecastModel? = null
    private var forecastModelArrayList: ArrayList<ForecastModel>? = null
    private var recyclerView: RecyclerView? = null
    private var forecastAdapter: ForecastAdapter? = null
    private var cityName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showing_weather_list)
        val intent = intent
        cityName = getIntent().getStringExtra("name")
        forecastModelArrayList = getIntent().getParcelableArrayListExtra("forecast")
        supportActionBar!!.title = getIntent().getStringExtra("name")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.forecast_recList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        forecastAdapter = ForecastAdapter(forecastModelArrayList!!, this@ShowingWeatherListActivity)
        recyclerView!!.adapter = forecastAdapter
        forecastAdapter!!.setClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?, position: Int) {
        val intent = Intent(this@ShowingWeatherListActivity, ForecastActivity::class.java)
        intent.putExtra("temp", forecastModelArrayList!![position].temp)
        intent.putExtra("feels_like", forecastModelArrayList!![position].feels_like)
        intent.putExtra("name", cityName)
        intent.putExtra("cloud", forecastModelArrayList!![position].cloud)
        intent.putExtra("description", forecastModelArrayList!![position].description)
        startActivity(intent)
    }

}