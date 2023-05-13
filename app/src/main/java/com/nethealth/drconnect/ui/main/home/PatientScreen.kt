package com.nethealth.drconnect.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.nethealth.drconnect.R
import com.nethealth.drconnect.app.state.DataStorage
import com.nethealth.drconnect.ui.theme.DrConnectTheme
import com.nethealth.drconnect.vo.Jwt
import kotlinx.coroutines.flow.first

@Composable
fun PatientScreen() {

    val context = LocalContext.current
    val dataStorage = remember { DataStorage(context) }
    val userDataState = remember { mutableStateOf(Jwt()) }
    val colors = listOf(Color(0xFF72DC8F), Color(0xFF06C395))

    val mSearchText = remember {
        mutableStateOf("")
    }

    val items = listOf(
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"
    )

    val filteredItems = items.filter { it.contains(mSearchText.value, ignoreCase = true) }

    DrConnectTheme {
        Column {

            Box(modifier = Modifier.background(colorResource(id = R.color.theme_green))) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {

                    Text(
                        text = "Patient",
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(32.dp),
                            )
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    ) {
                        BasicTextField(
                            value = mSearchText.value,
                            onValueChange = { newValue ->
                                mSearchText.value = newValue
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black,
                            ),
                            cursorBrush = SolidColor(Color.Black),
                            decorationBox = { innerTextField ->
                                if (mSearchText.value.isEmpty()) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart,
                                    ) {
                                        Text(
                                            text = stringResource(R.string.search_by_name_or_ur_number),
                                            fontSize = 16.sp,
                                            color = colorResource(id = R.color.placeholder_text),
                                        )
                                    }
                                }
                                innerTextField()
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                autoCorrect = false,
                                imeAction = ImeAction.Search
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }



            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(filteredItems) { item ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(
                            brush = Brush.horizontalGradient(colors),
                            shape = RoundedCornerShape(44.dp)
                        )
                        .clickable {
                            // Handle row click here
                        }) {

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
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

                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).weight(1f)
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                                Text(
                                    text = item,
                                    fontSize = 13.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                            }

                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = colorResource(id = R.color.white)
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(key1 = Unit) {
            val userData = dataStorage.getUserData.first()
            if (userData != null) {
                userDataState.value = userData
            }
        }

        if (!userDataState.value.accessToken.isNullOrEmpty()) {
            val userData = userDataState.value
            println("user data is: ${Gson().toJson(userData)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrConnectTheme {
        PatientScreen()
    }
}