package com.fooddonation.ui.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.fooddonation.R
import com.fooddonation.routing.Screen
import com.fooddonation.ui.restaurantPreference.RestaurantPreference
import com.fooddonation.ui.theme.FoodDonationAppTheme
import com.fooddonation.ui.theme.black
import com.fooddonation.ui.theme.white
import com.fooddonation.ui.theme.yellow
import com.fooddonation.utils.FoodDonationBorderField
import com.fooddonation.utils.RoundedButton
import com.fooddonation.utils.isValidEmail
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        RestaurantPreference(context)
    }
    var isUserFood by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val firebaseAuth = FirebaseAuth.getInstance()
    FoodDonationAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(yellow)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(yellow)
                        .height(60.dp)
                )
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_donation),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(5.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        FoodDonationBorderField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            placeholder = "Enter name",
                            keyboardType = KeyboardType.Text,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        FoodDonationBorderField(
                            value = email,
                            onValueChange = { text ->
                                email = text
                            },
                            placeholder = "Enter email",
                            keyboardType = KeyboardType.Email,
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        FoodDonationBorderField(
                            value = password,
                            onValueChange = { text ->
                                password = text
                            },
                            placeholder = "Enter Password",
                            keyboardType = KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        RoundedButton(
                            text = "Register",
                            onClick = {
                                if (name.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter name.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else if (email.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter email.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else if (isValidEmail(email.toString())) {
                                    Toast.makeText(
                                        context,
                                        "Please enter valid email.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else if (password.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter password.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else {
                                    isUserFood = true
                                    firebaseAuth.createUserWithEmailAndPassword(
                                        email.lowercase(),
                                        password
                                    )
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                preference.saveData(
                                                    "isLogin", true
                                                )
                                                Toast.makeText(
                                                    context,
                                                    "Register successfully.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate(
                                                    Screen.MainScreen.route
                                                ) {
                                                    popUpTo(Screen.RegisterScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                                isUserFood = false
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    task.exception?.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                isUserFood = false
                                            }
                                        }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Already have an account?",
                                textAlign = TextAlign.End,
                                style = TextStyle(color = black)
                            )

                            Text(
                                " Login", modifier = Modifier.clickable {
                                    navController.navigate(
                                        Screen.LoginScreen.route
                                    ) {
                                        popUpTo(Screen.RegisterScreen.route) {
                                            inclusive =
                                                true
                                        }
                                    }
                                }, textAlign = TextAlign.End,
                                style = TextStyle(color = black)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
            if (isUserFood) {
                Dialog(
                    onDismissRequest = { },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(white, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(color = yellow)
                    }
                }
            }
        }
    }
}