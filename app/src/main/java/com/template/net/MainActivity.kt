package com.template.net

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.template.net.http.BaseObserve
import com.template.net.http.NetClientManager
import com.template.net.http.api.WanAndroidApi
import com.template.net.http.api.entity.HomeChapterPageInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var requestNetButton: Button

    private val TAG = "MainActivity"

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
                            data?.let {
                                Log.i(TAG, "onSuccess: $it")
                            }
                            Toast.makeText(this@MainActivity, "请求ok", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFail(e: @NonNull Throwable?) {
                            Toast.makeText(this@MainActivity, "请求失败了", Toast.LENGTH_SHORT).show()
                            e?.let {
                                Log.e(TAG, "onFail: ", it)
                            }
                        }
                    })
        }
    }
}