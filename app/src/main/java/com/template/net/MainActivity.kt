package com.template.net

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.template.net.http.BaseObserve
import com.template.net.http.BaseResponse
import com.template.net.http.NetClientManager
import com.template.net.http.api.WanAndroidApi
import com.template.net.http.api.entity.HomeChapterPageInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var requestNetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestNetButton = findViewById(R.id.request_net);
        requestNetButton.setOnClickListener {

            NetClientManager.instance(WanAndroidApi::class.java)
                .getHomeList("0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : BaseObserve<HomeChapterPageInfo>() {

                        override fun onSuccess(data: HomeChapterPageInfo?) {
                            super.onSuccess(data)
                            Toast.makeText(this@MainActivity, "请求ok", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFail() {
                            Toast.makeText(this@MainActivity, "请求失败了", Toast.LENGTH_SHORT).show()
                        }


                    })
        }
    }
}