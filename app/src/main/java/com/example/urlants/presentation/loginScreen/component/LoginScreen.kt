package com.example.urlants.presentation.loginScreen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.urlants.presentation.ui.theme.*
import com.example.urlants.R
import com.example.urlants.common.Constants
import com.example.urlants.presentation.Screen
import com.example.urlants.presentation.loginScreen.LoginScreenViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(color = homeScreenBg)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = 5.dp)

        ) {
            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
        }

        Spacer(modifier = Modifier.padding(top = 30.dp, end = 0.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = null,
                modifier = Modifier.requiredWidth(350.dp),
                alignment = Alignment.TopCenter
            )
        }

        Spacer(modifier = Modifier.padding(top = 25.dp, end = 0.dp))
        Text(
            text = "Log In",
            color = Color.White,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(15.dp)
        )
        val focusManager = LocalFocusManager.current
        InputBox(
            input = viewModel.email,
            iconId = R.drawable.ic_email,
            title = "Email",
            onInputChange = { viewModel.email = it },
            keyBoardOpt = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            keyBoardAct = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            visualTransformation = VisualTransformation.None
        )
        InputBox(
            input = viewModel.password,
            iconId = R.drawable.ic_password_lock,
            title = "Password",
            onInputChange = { viewModel.password = it },
            keyBoardOpt = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            keyBoardAct = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(top = 25.dp, end = 0.dp))

        Button(
            shape = RoundedCornerShape(15.dp),
            onClick = {
                viewModel.getUserLogin(viewModel.email, viewModel.password)
                if (viewModel.logedIn) {
                    Log.d("LOGIN", "Email = ${viewModel.email} Password = ${viewModel.password}")
                    navController.navigate("${Screen.HomeScreen.route}/${Constants.PARAM_HOME_SCREEN_Login_ARG}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(85.dp)
                .padding(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = bottomsheet_short_btn),
            enabled = viewModel.state.value.button_active
        ) {
            Text(text = "Log in", color = Color.White)

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight()
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "Don't have an account? ", color = Color.White)
            Text(
                text = "Sign up",
                color = bottomsheet_short_btn,
                modifier = Modifier.clickable {
                    Log.d("TEXT", "Clicked on SIgnup")
                    navController.navigate(Screen.SignupScreen.route)
                }
            )

        }

    }
}
