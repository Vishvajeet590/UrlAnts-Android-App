package com.example.urlants.presentation.homeScreen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.urlants.R
import com.example.urlants.presentation.homeScreen.HomeScreenViewModel
import com.example.urlants.presentation.ui.theme.*

@Composable
fun AliasBox(homeScreenViewModel : HomeScreenViewModel, title : String){

    TextField(modifier = Modifier
        .padding(top = 15.dp, bottom = 15.dp, start = 10.dp,end = 10.dp)
        .fillMaxHeight()

        .border(color = urlBoxBg,width = 1.dp,shape = RoundedCornerShape(10.dp),),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = urlBoxHintText,
            textColor = urlBoxText,
            disabledTextColor = urlBoxHintText,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = bottomsheet_bg
        ),
        value = homeScreenViewModel.alias,
        onValueChange = { homeScreenViewModel.onAliasChanged(it) },

        placeholder = { Text(text = title,color = urlBoxHintText) }
    )
}