package com.example.ordercupcakeapp.ui.uiState

data class OrderUiState(
    /** Select cupcake quantity (1,6,12)*/
    val quantity: Int = 0,
    /** Flavour of the cupcakes in the order (such as "Chocolate", "Vanilla", etc..) */
    val flavour: String = "",
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: String = "",
    /** Available pickup dates for the order*/
    val sameDayPickUp: Boolean = false
)
