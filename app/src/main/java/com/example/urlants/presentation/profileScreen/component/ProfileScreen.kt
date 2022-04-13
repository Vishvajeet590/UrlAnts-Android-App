package com.example.urlants.presentation.profileScreen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.urlants.R
import com.example.urlants.common.Constants
import com.example.urlants.presentation.Screen
import com.example.urlants.presentation.profileScreen.ProfileScreenViewModel
import com.example.urlants.presentation.ui.theme.*
import okhttp3.internal.wait


@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .background(homeScreenBg)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Box {
            IconButton(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false, color = Color.LightGray),
                        onClick = { Log.d("URLLOGS", "checkkk") }),
                onClick = { Log.d("URLLOGS", "CLICKKKKIN") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {


                Text(
                    text = "Profile",
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewModel.state.value.avatarId?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            // .clip(CircleShape)
                            .border(width = 2.dp, color = Color.White, shape = CircleShape)
                            .padding(20.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                IconButton(onClick = {
                    viewModel.state.value.avatarId?.let {
                        viewModel.switchAvatar(
                            it
                        )
                    }
                }, modifier = Modifier.padding(end = 5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_repeat),
                        contentDescription = null
                    )
                }
            }
        }

        LazyColumn {

            item {
                viewModel.state.value.username?.let { InfoBox(title = "Username", info = it) }
            }
            item {
                viewModel.state.value.email?.let { InfoBox(title = "Email", info = it) }
            }
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
        ) {
            Button(
                onClick = {
                    viewModel.logOutUser()
                    if (!viewModel.logedIn) {
                        navController.navigate("${Screen.HomeScreen.route}/${Constants.PARAM_HOME_SCREEN_Logout_ARG}")
                    }
                },
                border = ButtonDefaults.outlinedBorder,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = homeScreenBg,
                    disabledBackgroundColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = bottomsheet_short_btn,
                        shape = RoundedCornerShape(10.dp)
                    ),

                ) {
                Text(
                    text = "Sign Out",
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}