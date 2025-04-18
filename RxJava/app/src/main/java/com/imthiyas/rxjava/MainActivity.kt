package com.imthiyas.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imthiyas.rxjava.databinding.ActivityMainBinding
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    companion object {
        const val TAG = "MainActivityTag"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        /*      simpleObserver()
              createObservable()*/

        /*  val button = findViewById<Button>(R.id.btn)
          button.clicks()
              .throttleFirst(1000, TimeUnit.MILLISECONDS)
              .subscribe {
                  Log.d(TAG, "Button Clicked")
              }*/


        val apiResponse = ProductItem(
            id = 1,
            image = "image.png",
            price = 12.2,
            title = "Title",
            category = "Category",
            description = "Description"
        )

        val request = 123
        val response = RetrofitClient.apiService.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, it.toString())
                it.toString()
            }

    }

    private fun createObservable() {
        val observable = Observable.create<String> {
            it.onNext("One")
            it.onNext("Two")
            it.onNext("Three")
            it.onNext("Four")
            it.onNext("Five")
            it.onComplete()
        }
    }

    private fun simpleObserver() {
        val list = listOf<String>("A", "B", "C", "D", "E", "F")
        val observable = Observable.fromIterable(list)
        observable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "${d.isDisposed}")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "${e.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete Called")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "${t}")
            }

        })
    }
}