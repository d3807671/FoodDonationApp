package com.fooddonation.ui.about_us

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fooddonation.R
import com.fooddonation.ui.theme.FoodDonationAppTheme
import com.fooddonation.ui.theme.black
import com.fooddonation.ui.theme.white
import com.fooddonation.ui.theme.yellow
import com.fooddonation.utils.FoodDonationBorderField
import com.fooddonation.utils.RoundedButton
import com.fooddonation.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutUsScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    FoodDonationAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = yellow)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "About Us", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = yellow,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.fillMaxSize().background(color = white)) {
                    Text(
                        "Food donation is a crucial part of helping vulnerable communities and needy people in a world where hunger and food insecurity still exist. Giving people their basic needs extends beyond just satisfying their bellies; it sustains lives, encourages optimism, and has a positive effect on society. The Narayan Seva Sansthan recognizes the crucial role that food contributions play in resolving the urgent problem of hunger and seeks to significantly improve the lives of people who are experiencing food scarcity.\nThe Significance of Donating for Food Donating food holds profound importance in several key ways:  \n" +
                                "\n" +
                                "Nourishing Lives: Food donations serve as a lifeline, providing sustenance to those who struggle to access nutritious meals.\n" +
                                "\n" +
                                "Combating Hunger: They contribute to the ongoing battle against hunger and malnutrition, making a tangible difference in the lives of individuals facing food insecurity.\n" +
                                "\n" +
                                "Promoting Well-being: Access to regular meals is crucial for maintaining good health and well-being, particularly for vulnerable populations.\n" +
                                "\n" +
                                "Fostering Solidarity: The act of donating food fosters a sense of community and solidarity, encouraging collective efforts to address food scarcity and support those in need.",
                        fontSize = 14.sp,
                        color = black,
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 15.dp),

                        )


                }
            }
        }



    }
}