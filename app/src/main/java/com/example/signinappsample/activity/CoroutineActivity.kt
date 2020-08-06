package com.example.signinappsample.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.signinappsample.R
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    val TAG = "CoroutineActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpCoroutine()
        //getJob()
    }

    private fun getJob() {
        //GlobalScope.launch always returns"job".
        //runBlocking blocks the main using... similar to Thread.sleep in main thread.
        val job = GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "on job return ")
        }

        runBlocking {
            Log.d(TAG, " onstart of runblock")
            job.join()
            Log.d(TAG, " after runblock")
        }
    }


    private fun setUpCoroutine() {
        GlobalScope.launch(Dispatchers.IO) {
            //A suspend function, can be given only inside coroutine.
            val getValue = doSomeAction()
            //Use async when returning any value. use (.await)
            val getValue1 = async { doSomeAction1() }

            delay(300L)
            Log.d(TAG, " Answer is : ${getValue}")
            Log.d(TAG, " Answer 1 is : ${getValue1.await()}")

            withContext(Dispatchers.Main) {
                //after IO operation set value in Main thread.
            }
        }
        Log.d(TAG, " current thread after coroutine : ${Thread.currentThread().name}")
    }


    suspend fun doSomeAction(): String {
        return "Testing"
    }

    suspend fun doSomeAction1(): String {
        return "Testing2"
    }
}