package com.example.ordercupcakeapp.viewmodel

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ordercupcakeapp.ui.uiState.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

private const val PRICE_PER_CUPCAKE = 2
private const val SAME_DAY_PICKUP = 3

class OrderCupcakeViewModel: ViewModel() {
    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState: StateFlow<OrderUiState> = _orderUiState.asStateFlow()

    val getDates: List<String> = getDates()

    private fun modifyState(
        quantity: Int = orderUiState.value.quantity,
        flavour: String = orderUiState.value.flavour,
        date: String = orderUiState.value.date,
        price: String = orderUiState.value.price,
        sameDayPickUp: Boolean = orderUiState.value.sameDayPickUp
        ) {
        _orderUiState.update { currentState ->
            currentState.copy(
                quantity = quantity,
                flavour = flavour,
                date = date,
                price = price,
                sameDayPickUp = sameDayPickUp,
            )
        }
    }

    fun modifyQuantity(
        quantity: Int
    ) {
        modifyState(quantity = quantity)
        modifyPrice(setPrice())
    }

    fun modifyFlavour(
        flavour: String,
    ) {
        modifyState(flavour = flavour)
    }

    fun modifyDate(
        date: String
    ) {
        if (date == getDates.first()) {
            modifySameDayPickup(true)
        } else modifySameDayPickup(false)
        modifyState(date = date)
        modifyPrice(setPrice())
    }

    fun modifyPrice(
        price: String
    ) {
        modifyState(price = price)
    }

    fun modifySameDayPickup(sameDayPickUp: Boolean) {
        modifyState(sameDayPickUp = sameDayPickUp)
    }

    fun setPrice(): String {
        return if (orderUiState.value.sameDayPickUp) {
            (PRICE_PER_CUPCAKE * orderUiState.value.quantity + SAME_DAY_PICKUP).toString()
        } else return (PRICE_PER_CUPCAKE * orderUiState.value.quantity).toString()
    }

    private fun getDates(): List<String> {
        val calendar = Calendar.getInstance()
        val allDates = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        repeat(7) {
            allDates.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return allDates
    }

    fun resetOrder() {
        _orderUiState.value = OrderUiState(
            quantity = 0,
            flavour = "",
            date = "",
            price = "",
        )
    }

//    init {
//        resetOrder()
//    }
}