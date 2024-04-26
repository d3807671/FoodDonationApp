package com.fooddonation.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import java.util.*

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
    var quality by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    var selectTime by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }

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
                FoodDonationBorderField(
                    value = if (selectedDate != "") selectedDate else "Select Date",
                    onValueChange = { text ->
                        selectedDate = text
                    },
                    onClick = {
                        showDatePicker = true
                    },
                    isEnabled = false,
                    placeholder = "Select Date"
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (showDatePicker) {
                    context.DatePickerDialogBox(onDateSelect = {
                        selectedDate = it
                        showDatePicker = false
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                FoodDonationBorderField(
                    value = if (selectTime != "") selectTime else "Select Time",
                    onValueChange = { text ->
                        selectTime = text
                    },
                    onClick = {
                        showTimePicker = true
                    },
                    isEnabled = false,
                    placeholder = "Select Time"
                )
                if (showTimePicker) {
                    context.TimePickerDialogBox {
                        selectTime = it
                        showTimePicker = false
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Quality",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    value = quality,
                    placeholder = {
                        Text("Enter quality", fontSize = 16.sp)
                    },
                    onValueChange = { quality = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    maxLines = 3
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
                            } else if (mobile.length<10) {
                                Toast.makeText(context, "Please enter valid mobile.", Toast.LENGTH_SHORT)
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
                            }  else if (selectedDate.isEmpty()) {
                                Toast.makeText(context, "Please select date.", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (selectTime.isEmpty()) {
                                Toast.makeText(context, "Please select time.", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (quality.isEmpty()) {
                                Toast.makeText(context, "Please enter quality.", Toast.LENGTH_SHORT)
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

@Composable
fun Context.DatePickerDialogBox(
    onDateSelect: (String) -> Unit
) {
    val year: Int
    val month: Int
    val day: Int
    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mDatePickerDialog = DatePickerDialog(
        this,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateSelect("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, year, month, day
    )
    mDatePickerDialog.show()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Context.TimePickerDialogBox(
    onTimeSelect: (String) -> Unit
) {
    val mCalendar = Calendar.getInstance()
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        this,
        {_, mHour : Int, mMinute: Int ->
            onTimeSelect("$mHour:$mMinute")
        }, hour, minute, false
    )
    mTimePickerDialog.show()
}