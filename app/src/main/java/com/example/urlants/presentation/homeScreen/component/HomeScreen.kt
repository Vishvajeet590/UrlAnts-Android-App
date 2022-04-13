package com.example.urlants.presentation.homeScreen.component

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.urlants.R
import com.example.urlants.common.Constants
import com.example.urlants.data.remote.dto.Statl
import com.example.urlants.presentation.Screen
import com.example.urlants.presentation.homeScreen.HomeScreenViewModel
import com.example.urlants.presentation.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    arg : String?,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.state
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    Log.d("HOME SCREEN","RUNNING AGAIN")
    viewModel.checkJWT()
    viewModel.checkLogedIn()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(200.dp),
                backgroundColor = bottomsheet_bg,
                shape = RoundedCornerShape(15.dp),

                ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sheet_chin),
                        contentDescription = null,
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    )
                    UrlEditText(homeScreenViewModel = viewModel, "Enter your very long URL...")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(85.dp),
                        horizontalArrangement = Arrangement.Start,

                        ) {
                        AliasBox(homeScreenViewModel = viewModel, title = "Alias...")
                        Button(
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(top = 15.dp, bottom = 15.dp, end = 10.dp),
                            onClick = { viewModel.getShortUrl(viewModel.longUrl, viewModel.alias) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = bottomsheet_short_btn),
                            enabled = viewModel.state.value.shortBtnEnable
                        ) {
                            Text(
                                text = "Short",
                                color = Color.White,
                                style = MaterialTheme.typography.button
                            )
                        }
                    }
                }


            }
        },
        sheetPeekHeight = 50.dp,
        sheetElevation = 5.dp,
        drawerGesturesEnabled = true,
        sheetBackgroundColor = bottomsheet_bg,
        sheetShape = RoundedCornerShape(10.dp)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = homeScreenBg)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_home_screen_dots
                    ), contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(20.dp)
                )
                Row() {

                    IconButton(
                        onClick = {
                            Log.d("ARGS","In Home onClick arg = $arg")
                            navController.navigate(viewModel.profile_login_nav)
                            /*if (arg.equals(Constants.PARAM_HOME_SCREEN_Logout_ARG)){
                                navController.navigate(Screen.LoginScreen.route)
                            }else if((arg.equals(Constants.PARAM_HOME_SCREEN_Login_ARG))){
                                navController.navigate(Screen.ProfileScreen.route)
                            }*/

                        },
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_avatarf),
                            contentDescription = null,
                            modifier = Modifier.requiredHeight(30.dp)
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.requiredHeight(30.dp)
                        )
                    }
                }
            }
            AnimatedVisibility(visible = viewModel.state.value.showList) {
                Text(
                    text = "Hi, ${viewModel.userName}",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(visible = viewModel.state.value.showEmptyImage) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_empty_list
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp)
                        )

                        Text(
                            text = "${viewModel.state.value.noListStatement} \n Still you can Short your url. Just swipe up from bottom of screen.",
                            color = urlBoxHintText,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                AnimatedVisibility(
                    visible = viewModel.state.value.showList
                ) {
                    LazyColumn(
                    ) {
                        Log.d("URLLOGS", "${viewModel.state.value.urlList?.size}")
                        items(
                            items = viewModel.state.value.urlList ?: emptyList()
                        ) { url ->

                            Url_item(shortUrl = url, viewModel = viewModel)
                        }
                    }
                }


                AnimatedVisibility(visible = viewModel.state.value.showCard) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        viewModel.state.value.urlItem?.let { it1 ->
                            Url_item(
                                shortUrl = it1,
                                viewModel = viewModel
                            )
                        }

                        Text(
                            text = "Copy or Login to save this url on app",
                            color = urlBoxHintText,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }


            }


        }

    }
}




