package com.nethealth.drconnect.ui.auth.login

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nethealth.drconnect.R
import com.nethealth.drconnect.app.state.DataStorage
import com.nethealth.drconnect.data.remote.Resource
import com.nethealth.drconnect.ui.auth.AuthViewModel
import com.nethealth.drconnect.ui.main.home.MainActivity
import com.nethealth.drconnect.ui.nav.Screen
import com.nethealth.drconnect.ui.theme.DrConnectTheme
import com.nethealth.drconnect.ui.util.Constant
import com.nethealth.drconnect.vo.Jwt
import com.nethealth.drconnect.vo.JwtBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val mEmail = remember {
        mutableStateOf("")
    }

    val mPw = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val authViewModel: AuthViewModel = hiltViewModel()
    val loginResult by authViewModel.loginResult.collectAsState()

    val isLoading = remember { mutableStateOf(false) }
    val dataStorage = remember { DataStorage(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(44.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Image(painterResource(id = R.drawable.img_logo), contentDescription = null)

            Spacer(modifier = Modifier.height(height = 8.dp))

            Text(
                text = "Hello Doctor!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.dark_text)
            )

            Text(
                text = "Welcome",
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 16.dp),
                color = colorResource(R.color.dark_text)
            )

            Text(
                text = "Please sign in to continue",
                color = colorResource(R.color.light_text)
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                value = mEmail.value,

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor =
                       if (mEmail.value.isNotEmpty() && !isEmailValid(mEmail.value)){
                           Color.Red
                       }else
                           colorResource(R.color.theme_green)
                    ,
                    unfocusedBorderColor = colorResource(R.color.light_text)),

                onValueChange = {
                    mEmail.value = it
                },

                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_your_email),
                        color = colorResource(R.color.placeholder_text)
                    )
                },

                shape = RoundedCornerShape(32.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )

            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                value = mPw.value,

                onValueChange = {
                    mPw.value = it
                },

                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        color = colorResource(R.color.placeholder_text)
                    )
                },
                shape = RoundedCornerShape(32.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),

                visualTransformation =  PasswordVisualTransformation()

            )

            Spacer(modifier = Modifier.height(height = 12.dp))

            LoadingButton(text =  "Sign In".uppercase(), isLoading = isLoading.value) {
                isLoading.value = true
                authViewModel.getJwtToken(
                    JwtBody(
                        mEmail.value,
                        mPw.value,
                        "NetHealthConnectApp", //clientId
                        "6C4A5DC48833334ABAF9A27CD593F217" //apikey
                    )
                )
            }

            when(loginResult){
                is Resource.Success<*> -> {
                    isLoading.value = false

                    val jwt = (loginResult as Resource.Success<Jwt>).data
                    if (!jwt?.accessToken.isNullOrEmpty()){
                        LaunchedEffect(key1 = Unit){
                            dataStorage.saveUserData(jwt!!)
                        }

                        context.startActivity(Intent(context, MainActivity::class.java))
                        (context as Activity).finish()
                    }
                }
                is Resource.Error<*> -> {
                    isLoading.value = false
                    val errorMessage = (loginResult as Resource.Error<*>)
                    println("error:::: ${errorMessage.msg}")
                    ErrorDialog(error = errorMessage.error.toString())
                }

                is Resource.Loading -> {}

                else -> {}
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.theme_green)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.i_forgot_my_password),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    fontSize = 13.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .clickable {
                        navController.navigate(Screen.Forgot.route)
                    },
                )
        }
    }
}

@Composable
fun LoadingButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.theme_green),
            contentColor = Color.White)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                color = colorResource(R.color.theme_green)
            )
        } else {
            Text(text = text)
        }
    }
}

@Composable
fun ErrorDialog(error: String){
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value){
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                })
                { Text(text = "CLOSE") }
            },
            title = { Text(text = "Error") },
            text = { Text(text = error) }
        )
    }
}


fun isEmailValid(email: String): Boolean {
    return Constant.EMAIL_REGEX.toRegex().matches(email)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrConnectTheme {
        LoginScreen(navController = rememberNavController())
    }
}