package com.example.ordercupcakeapp.app

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ordercupcakeapp.R
import com.example.ordercupcakeapp.data.DataSource
import com.example.ordercupcakeapp.ui.components.AppBar
import com.example.ordercupcakeapp.ui.pages.ChooseFlavour
import com.example.ordercupcakeapp.ui.pages.OrderSummary
import com.example.ordercupcakeapp.ui.pages.PickupDate
import com.example.ordercupcakeapp.ui.pages.StartOrderScreen
import com.example.ordercupcakeapp.utils.Screen
import com.example.ordercupcakeapp.viewmodel.OrderCupcakeViewModel

@Composable
fun OrderApp(
    modifier: Modifier = Modifier,
    viewModel: OrderCupcakeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val orderState by viewModel.orderUiState.collectAsState()

    val flavours = DataSource.flavours.map { eachFlavour -> LocalContext.current.getString(eachFlavour) }
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )

    Scaffold (
        topBar = { AppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() }
        ) },
        modifier = modifier.fillMaxSize()
    ){ innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = Screen.Start.name) {
                StartOrderScreen(
                    chooseOrder = { viewModel.modifyQuantity(it) },
                    onNextButtonClick = {navController.navigate(Screen.Flavour.name)},
                    onCancelButtonClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )
            }

            composable(route = Screen.Flavour.name) {
                ChooseFlavour(
                    flavour = orderState.flavour,
                    chooseOrder = { viewModel.modifyFlavour(it) },
                    flavourOptions = flavours,
                    subTotal = orderState.price,
                    onNextButtonClick = { navController.navigate(Screen.Pickup.name) },
                    onCancelButtonClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )
            }

            composable(route = Screen.Pickup.name) {
                PickupDate(
                    date = orderState.date,
                    setDate = { viewModel.modifyDate(it) },
                    subTotal = orderState.price,
                    dates = viewModel.getDates,
                    onNextButtonClick = { navController.navigate(Screen.Summary.name) },
                    onCancelButtonClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )
            }

            composable(route = Screen.Summary.name) {
                val context = LocalContext.current
                OrderSummary(
                    quantity = orderState.quantity,
                    flavour = orderState.flavour,
                    pickupDate = orderState.date,
                    subTotal = orderState.price,
                    onSendButtonClick = { subject: String, summary: String ->
                        shareOrder(context, subject = subject, summary = summary)
                    },
                    onCancelButtonClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderCupcakeViewModel,
    navController: NavHostController,
) {
    viewModel.resetOrder()
    navController.popBackStack(Screen.Start.name, false)
}

private fun shareOrder(context: Context , subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}
