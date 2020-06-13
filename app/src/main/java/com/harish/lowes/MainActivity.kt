package com.harish.lowes

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.harish.lowes.model.ForecastModel
import com.harish.lowes.utils.MyUtils
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TEMPERATURE_UNITS = "&units=imperial"

    private var cityNameEditText: EditText? = null
    private var lookUpButton: Button? = null
    private var cityName: String? = null
    private var temp = ""
    private var cloud = ""
    private var forecast = ""
    private var cityData = ""
    private var description = ""
    private var progressDialog: ProgressDialog? = null
    private var queue: RequestQueue? = null
    private var forecastModelList: ArrayList<ForecastModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        cityNameEditText = findViewById(R.id.editText_cityName)
        lookUpButton = findViewById(R.id.btnShow)
        lookUpButton!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        cityName = cityNameEditText!!.text.toString()
        if (MyUtils.isOnline(this)) {
            loadCity(cityName!!)
        } else {
            Toast.makeText(this, "Please check the internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCity(city: String) {
        forecastModelList = ArrayList()
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Please wait...")
        progressDialog!!.show()
        progressDialog!!.setCancelable(false)
        queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "+&appid=" + MyUtils.API_KEY
                + TEMPERATURE_UNITS,
                Response.Listener { response ->
                    progressDialog!!.cancel()
                    Log.e("Response", "" + response)
                    try {
                        var forecastModel: ForecastModel? = null
                        val jsonObject = JSONObject(response)
                        val listJsonArray = jsonObject.getJSONArray("list")
                        for (i in 0 until listJsonArray.length()) {
                            forecastModel = ForecastModel()
                            val jo = listJsonArray.getJSONObject(i)
                            val mainJsonObject = jo.getJSONObject("main")
                            temp = mainJsonObject.getString("temp")
                            Log.e("TEMP", "" + temp)
                            forecast = mainJsonObject.getString("feels_like")
                            Log.e("forcast", "" + forecast)
                            val weatherJsonArray = jo.getJSONArray("weather")
                            for (j in 0 until weatherJsonArray.length()) {
                                val weatherObject = weatherJsonArray.getJSONObject(j)
                                cloud = weatherObject.getString("main")
                                description = weatherObject.getString("description")
                            }
                            forecastModel.temp = temp
                            forecastModel.feels_like = forecast
                            forecastModel.cloud = cloud
                            forecastModel.description = description
                            forecastModelList!!.add(forecastModel)
                        }
                        val jsonObjectCity = jsonObject.getJSONObject("city")
                        cityData = jsonObjectCity.getString("name")
                        val intent = Intent(this, ShowingWeatherListActivity::class.java)
                        intent.putParcelableArrayListExtra("forecast", forecastModelList)
                        intent.putExtra("name", cityData)
                        startActivity(intent)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { error ->
            progressDialog!!.cancel()
            Log.d("error", error.toString())
            Toast.makeText(this, "Please Enter Correct City Name..", Toast.LENGTH_SHORT).show()
        })
        queue!!.add(request)
    }
}