package com.example.urlants.presentation.loginScreen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.urlants.presentation.ui.theme.*

@Composable
fun InputBox(input : String ,iconId : Int,title :String, onInputChange: (String) -> Unit, keyBoardOpt : KeyboardOptions, keyBoardAct : KeyboardActions, visualTransformation: VisualTransformation ){

    TextField(modifier = Modifier
        .paddingFromBaseline(15.dp)
        .requiredHeight(75.dp)
        .padding(10.dp)
        .fillMaxWidth()
        .wrapContentHeight(unbounded = true)
        .border(color = Color.White,width = 1.dp,shape = RoundedCornerShape(10.dp),),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = urlBoxHintText,
            textColor = urlBoxText,
            disabledTextColor = urlBoxHintText,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        value = input,
        onValueChange = onInputChange,
        leadingIcon = {
                Icon(painter = painterResource(id = iconId), contentDescription = null,tint = Color.White )
        },
        placeholder = { Text(text = title,color = urlBoxHintText) },
        keyboardOptions = keyBoardOpt,
        singleLine = true,
        keyboardActions = keyBoardAct,
        visualTransformation = visualTransformation,
    )

}