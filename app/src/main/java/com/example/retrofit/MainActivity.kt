package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.Adapter.PostAdapter
import com.example.retrofit.Retrofit.IMyAPI
import com.example.retrofit.Retrofit.RetrofitClient
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private var binding :ActivityMainBinding ?=null
    private var adapter : PostAdapter?= null
    var jsonApi : IMyAPI?=null
    var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Init API
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyAPI::class.java)

        binding!!.recylerData.setHasFixedSize(true)
        binding!!.recylerData.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable!!.addAll(jsonApi!!.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{posts->displayData(posts)})
    }

    private fun displayData(posts: List<Post>?) {
        adapter = PostAdapter(posts!!)
        binding!!.recylerData.adapter = adapter
    }
}