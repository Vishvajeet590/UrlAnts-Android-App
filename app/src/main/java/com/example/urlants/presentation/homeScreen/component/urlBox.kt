package com.example.urlants.presentation.homeScreen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.urlants.R
import com.example.urlants.presentation.homeScreen.HomeScreenState
import com.example.urlants.presentation.homeScreen.HomeScreenViewModel
import com.example.urlants.presentation.ui.theme.*

@Composable
fun UrlEditText(homeScreenViewModel: HomeScreenViewModel, title : String){

    TextField(modifier = Modifier
        .requiredHeight(85.dp)
        .padding(top = 15.dp, bottom = 15.dp, start = 10.dp,end = 10.dp)
        .fillMaxWidth()
        .wrapContentHeight(unbounded = true)
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
        value = homeScreenViewModel.longUrl,
        onValueChange = { homeScreenViewModel.onTextChanged(it) },
        trailingIcon = {
            if (homeScreenViewModel.longUrl.isNotEmpty()) {
                IconButton(onClick = { homeScreenViewModel.longUrl = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        tint = urlBoxText,
                        contentDescription = null
                    )
                }
            }else{
                Icon(painter = painterResource(id = R.drawable.ic_link), contentDescription = null,tint = urlBoxLink )
            }
        },
        placeholder = { Text(text = title,color = urlBoxHintText) },
        singleLine = true
    )
}