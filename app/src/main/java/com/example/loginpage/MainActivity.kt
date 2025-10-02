package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginpage.ui.theme.LoginPageTheme


fun areCredentialsNotEmpty(username: String, password: String): Boolean {
    return username.isNotBlank() && password.isNotBlank()
}

@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginStatus by remember { mutableStateOf("") }
    val isLoginEnabled = areCredentialsNotEmpty(username, password)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Halaman Login",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // 2. Input Password (Mode Password)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                if (username == "admin" && password == "rahasia") {
                    loginStatus = "Login Berhasil! 🎉"
                } else {
                    loginStatus = "Login Gagal. Username atau Password salah."
                }
            },
            enabled = isLoginEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("LOGIN")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = loginStatus)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginPageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPageTheme {
        LoginPage()
    }
}