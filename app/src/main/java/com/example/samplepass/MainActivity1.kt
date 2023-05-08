import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.font.FontWeight.Companion.Bold

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
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                "\n\nEnter your New password :\n",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = Bold,
            )
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }
            var confirmVisibility by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }

            errorMessage = ""

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "New Password") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password") },
                visualTransformation = if (confirmVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confirmVisibility = !confirmVisibility }) {
                        Icon(
                            if (confirmVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )

            if (password != confirmPassword) {
                errorMessage = "Please Double Check Your Password"
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontWeight = Bold,
                        fontSize = 15.sp,
                    )
                }
            }

            // Password strength check
            val passwordContainsLetter by remember { derivedStateOf { password.contains(Regex("[a-zA-Z]")) } }
            val passwordContainsDigit by remember { derivedStateOf { password.contains(Regex("[0-9]")) } }
            val passwordLengthOk by remember { derivedStateOf { password.length >= 8 } }
            val passwordContainsSpecialChar by remember { derivedStateOf { password.contains(Regex("[^a-zA-Z0-9]")) } }
//            val passwordNotCommon by remember { derivedStateOf { !commonPasswords.contains(password.lowercase()) } }

            Text(
                text = "\n\nPassword Strength Check:",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)

            )

            Text("\t\t\t\t\t\t\t\t${if (passwordContainsLetter) "✅" else "❌"}\tContains at least one letter: ",fontSize = 17.sp,style = if (passwordContainsLetter) MaterialTheme.typography.body1 else MaterialTheme.typography.body2)
            Text("\t\t\t\t\t\t\t\t${if (passwordContainsDigit) "✅" else "❌"}\tContains at least one digit:",fontSize = 17.sp ,style = if (passwordContainsDigit) MaterialTheme.typography.body1 else MaterialTheme.typography.body2)
            Text("\t\t\t\t\t\t\t\t${if (passwordLengthOk) "✅" else "❌"}\tIs at least 8 characters long:",fontSize = 17.sp, style = if (passwordLengthOk) MaterialTheme.typography.body1 else MaterialTheme.typography.body2)
            Text("\t\t\t\t\t\t\t\t${if (passwordContainsSpecialChar) "✅" else "❌"}\tContains at least one special character:",fontSize = 17.sp, style = if (passwordContainsSpecialChar) MaterialTheme.typography.body1 else MaterialTheme.typography.body2)
        }
    }


            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 800.dp)
            )
            {
                Text(
                    "Save",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    textAlign = TextAlign.Center
                )
            }

        }

