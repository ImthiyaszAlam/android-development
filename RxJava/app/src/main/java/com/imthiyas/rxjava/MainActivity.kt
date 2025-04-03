package com.imthiyas.rxjava

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imthiyas.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    companion object {
        const val TAG = "MainActivityTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        simpleObserver()
        createObservable()
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