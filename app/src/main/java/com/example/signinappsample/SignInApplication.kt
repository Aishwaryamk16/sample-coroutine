package com.example.signinappsample

import android.app.Activity
import android.app.Application
import android.os.Bundle


class SignInApplication : Application() {
    var mActivity: Activity? = null
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
                mActivity = null;
            }

            override fun onActivityStarted(activity: Activity) {
                mActivity = activity; }

            override fun onActivityDestroyed(p0: Activity) {
                //nothing to do
          }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                //nothing to do
            }

            override fun onActivityStopped(p0: Activity) {
                //nothing to do
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                //nothing to do
            }

            override fun onActivityResumed(activity: Activity) {
                mActivity = activity; }

        })

    }
}