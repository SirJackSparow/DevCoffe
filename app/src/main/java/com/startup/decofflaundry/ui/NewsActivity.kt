package com.startup.decofflaundry.ui

import android.view.LayoutInflater
import com.startup.decofflaundry.base.BaseViewBindingActivity
import com.startup.decofflaundry.databinding.ActivityNewsBinding

class NewsActivity : BaseViewBindingActivity<ActivityNewsBinding>() {

    override fun onInit() {

    }

    override fun getActivityBinding(layoutinflater: LayoutInflater): ActivityNewsBinding =
        ActivityNewsBinding.inflate(layoutInflater)

}
