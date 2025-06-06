package com.example.ordercupcakeapp.utils

import androidx.annotation.StringRes
import com.example.ordercupcakeapp.R

enum class Screen(@StringRes val title: Int) {
    Start(R.string.app_name),
    Flavour(R.string.choose_flavour),
    Pickup(R.string.choose_pickup_date),
    Summary(R.string.order_summary)
}