package com.nethealth.drconnect.ui.main.drawer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nethealth.drconnect.R

@Composable
fun DrawerHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth().padding(start = 16.dp, top = 44.dp, bottom = 8.dp)
        , horizontalAlignment = Alignment.Start
    ) {

        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "profile pic",
            modifier = Modifier
                .clip(CircleShape)
                .width(40.dp)
                .height(40.dp)
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Doctor Name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_text)
        )

        Text(text = "dr.clinica@mail.net", fontSize = 14.sp, color = Color.Gray)
    }

}