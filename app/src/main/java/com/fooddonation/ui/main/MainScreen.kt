package com.fooddonation.ui.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fooddonation.R
import com.fooddonation.routing.Screen
import com.fooddonation.ui.drawer.DrawerBody
import com.fooddonation.ui.drawer.DrawerHeader
import com.fooddonation.ui.drawer.TopBar
import com.fooddonation.ui.restaurantPreference.RestaurantPreference
import com.fooddonation.ui.theme.FoodDonationAppTheme
import com.fooddonation.ui.theme.black
import com.fooddonation.ui.theme.white
import com.fooddonation.ui.theme.yellow
import com.fooddonation.utils.FoodDonationBorderField
import com.fooddonation.utils.RoundedButton
import com.fooddonation.utils.isValidEmail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        RestaurantPreference(context)
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    var isSubmit by remember {
        mutableStateOf(false)
    }
    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    FoodDonationAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = white),
            drawerContent = {
                DrawerHeader()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = white)
                ) {
                    DrawerBody(onContact = {
                        navController.navigate(Screen.ContactUsScreen.route)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }, onAbout = {
                        navController.navigate(Screen.AboutUsScreen.route)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },onLogout = {
                        isLogout = true
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    })
                }

            },
            backgroundColor = yellow,
            contentColor = yellow,
            drawerContentColor = white,
            drawerBackgroundColor = yellow
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(white)
                    .padding(start=10.dp,end=10.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(10.dp))
                Text(
                    "Name",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FoodDonationBorderField(
                    value = name,
                    onValueChange = { text ->
                        name = text
                    },
                    placeholder = "Enter name",
                    keyboardType = KeyboardType.Text,
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Mobile",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FoodDonationBorderField(
                    value = mobile,
                    onValueChange = { text ->
                        mobile = text
                    },
                    placeholder = "Enter mobile",
                    keyboardType = KeyboardType.Phone,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Email",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FoodDonationBorderField(
                    value = email,
                    onValueChange = { text ->
                        email = text
                    },
                    placeholder = "Enter email",
                    keyboardType = KeyboardType.Email,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Address",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FoodDonationBorderField(
                    value = address,
                    onValueChange = { text ->
                        address = text
                    },
                    placeholder = "Enter address",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Address",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FoodDonationBorderField(
                    value = address,
                    onValueChange = { text ->
                        address = text
                    },
                    placeholder = "Enter address",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                ) {
                    RoundedButton(
                        text = "Submit",
                        textColor = white,
                        onClick = {
                            if (name.isEmpty()) {
                                Toast.makeText(context, "Please enter name.", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (mobile.isEmpty()) {
                                Toast.makeText(context, "Please enter mobile.", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (email.isEmpty()) {
                                Toast.makeText(context, "Please enter email.", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (isValidEmail(email.trim())) {
                                Toast.makeText(
                                    context,
                                    "Please enter valid email.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (address.isEmpty()) {
                                Toast.makeText(context, "Please enter address.", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                isSubmit = true
                            }

                        }
                    )
                }
            }
        }

        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    RoundedButton(
                        text = "Logout",
                        textColor = white,
                        onClick = {
                            preferenceManager.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        }
                    )

                },
                dismissButton = {
                    RoundedButton(
                        text = "Cancel",
                        textColor = white,
                        onClick = { isLogout = false }
                    )
                }
            )
        }
        if (isSubmit) {
            AlertDialog(
                onDismissRequest = {
                    isSubmit = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("You have successfully submitted detail.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = white,
                        onClick = {
                            navController.navigate(Screen.ThankScreen.route)
                            isSubmit = false
                        }
                    )
                },
                dismissButton = {}
            )
        }
    }


}

