package com.mughitszufar.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mughitszufar.newsapi.databinding.ActivityMainBinding
import com.mughitszufar.newsapi.model.ResponseNews
import com.mughitszufar.newsapi.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterRv:CdvNewsHeadlinesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapterRv = CdvNewsHeadlinesAdapter()
        binding.run {
            setContentView(root)
            setSupportActionBar(toolBar)

//           untuk membuild recyclerview
            mainRv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterRv

            }

            val call = RetrofitBuilder.getService().fetchHeadlines()
            call.enqueue(object : Callback<ResponseNews>{
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Timber.e(t)
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    response.body()?.articles?.let {
                        adapterRv.addData(it)
                    }
                }
            })
        }
    }
}