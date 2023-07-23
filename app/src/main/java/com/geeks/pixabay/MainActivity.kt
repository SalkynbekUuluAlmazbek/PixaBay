package com.geeks.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.geeks.pixabay.databinding.ActivityMainBinding
import com.geeks.pixabay.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(arrayListOf())
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            buttonSearch.setOnClickListener {
                getImage()
            }
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        ++page
                        getImage()

                    }
                }

            })
        }
    }
                    private fun ActivityMainBinding.getImage() {
                RetrofitService.api.getPictures(editTextWord.text.toString(), Page = page)
                    .enqueue(object : Callback<PixaModel> {
                        override fun onResponse(
                            call: Call<PixaModel>,
                            response: Response<PixaModel>
                        ) {
                            if (response.isSuccessful)
                                recyclerView.adapter = adapter
                            adapter.list.clear()
                            adapter.list.addAll(response.body()?.hits!!)

                        }

                        override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                        }

                    })
            }
        }
