package com.example.urlants.presentation.homeScreen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.urlants.R
import com.example.urlants.common.Constants
import com.example.urlants.data.remote.dto.Statl
import com.example.urlants.presentation.homeScreen.HomeScreenViewModel
import com.example.urlants.presentation.ui.theme.*


@Composable
fun Url_item(shortUrl :Statl , viewModel : HomeScreenViewModel ){
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = url_item_bg
    ){

        Column (Modifier.padding(10.dp)){
            Row {

                Box(modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = list_chip,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)){
                    Text(
                        text = "Active" ,
                        fontSize = 15.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }



                Text(modifier = Modifier.padding(start = 10.dp,top = 5.dp,end = 35.dp),text = "${Constants.BASE_URL}/${shortUrl.shortUrl}",fontSize = 16.sp,color = Color.White,maxLines = 1)

            }


            Text(text = shortUrl.longUrl, fontSize = 16.sp,color = Color.White,modifier = Modifier.padding(start = 5.dp,top=10.dp,end = 50.dp,bottom = 10.dp),maxLines = 2)


        }
        Box(modifier = Modifier
            .padding(end = 10.dp,top = 25.dp,bottom = 10.dp)
            , contentAlignment = Alignment.BottomEnd){
            IconButton(onClick = { viewModel.copyUrl(shortUrl.shortUrl)} ) {
            Icon(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null,tint = list_qr,)
            }


        }

    }

}


