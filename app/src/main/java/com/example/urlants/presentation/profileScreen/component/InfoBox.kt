package com.example.urlants.presentation.profileScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.urlants.presentation.ui.theme.list_item_bg
import com.example.urlants.presentation.ui.theme.urlBoxHintText

@Composable
fun InfoBox(title : String,info : String){
    Card(
        backgroundColor = list_item_bg,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column() {
            Text(
                text = title,
                color = urlBoxHintText,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top=10.dp,start = 10.dp,end = 10.dp,bottom = 1.dp)
            )

            Text(
                text = info,
                color = Color.White,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(top=1.dp,start = 10.dp,end = 10.dp,bottom = 10.dp)
            )
        }

    }
}