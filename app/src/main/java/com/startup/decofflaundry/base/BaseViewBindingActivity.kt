package com.startup.decofflaundry.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<vb : ViewBinding> : AppCompatActivity() {

    lateinit var bind : vb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = getActivityBinding(layoutInflater)
        val view  = bind.root
        setContentView(view)
        onInit()
    }

    abstract fun onInit()
    abstract fun getActivityBinding(layoutinflater: LayoutInflater) : vb
}