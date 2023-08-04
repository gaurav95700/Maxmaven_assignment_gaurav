package com.example.maxmaven_assignment_gaurav

import android.app.Application

class BookApplication : Application() {


    companion object {
        lateinit var INSTANCE: BookApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}