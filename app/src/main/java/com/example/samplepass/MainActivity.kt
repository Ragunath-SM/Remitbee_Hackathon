package com.example.samplepas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ChangePasswordScreen(navController: NavHostController? = null) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Change My Password") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.Help, contentDescription = "Help")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "\nEnter your Current password :\n",
                color = Color.Blue,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
            )
            var password by rememberSaveable { mutableStateOf("") }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }
            var isPasswordCorrect by remember { mutableStateOf(false) }

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = ""
                },
                label = { Text("Password") },
                singleLine = true,
                placeholder = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .size(width = 400.dp, height = 60.dp),
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            )

            if (password.isNotEmpty() && password != "Ragunath") {
                errorMessage = "You entered the wrong password"
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            } else {
                errorMessage = "password was correct"
                if (errorMessage.isEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Green,
                    )
                }
            }

            Button(
                onClick = { isPasswordCorrect = password == "Ragunath"
                    navController?.navigate("SecondScreen")},
                enabled = password.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(backgroundColor = if (isPasswordCorrect) Color.Gray else Color.Blue),
//                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Blue),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 560.dp)
                    .size(80.dp)
                    .padding(16.dp),
            ) {
                Text(text = "Continue", color=Color.White,fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,)
            }

        }
    }
}






