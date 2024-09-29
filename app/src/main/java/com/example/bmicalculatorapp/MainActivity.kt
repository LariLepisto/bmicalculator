package com.example.bmicalculatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculatorapp.ui.theme.BmiCalculatorAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiCalculatorAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiCalculatorAppTheme {
        Bmi()
    }
}

@Composable
fun Bmi() {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }

    // Convert height and weight from input strings to float
    val height = heightInput.toFloatOrNull() ?: 0f
    val weight = weightInput.toFloatOrNull() ?: 0f

    // Calculate BMI if height and weight are provided
    val bmi = if (height > 0f) weight / (height * height) else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BMI Calculator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Height input field
        OutlinedTextField(
            value = heightInput,
            onValueChange = { input ->
                heightInput = input.replace(',', '.')
            },
            label = { Text("Height (meters)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weight input field
        OutlinedTextField(
            value = weightInput,
            onValueChange = { input ->
                weightInput = input.replace(',', '.')
            },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Display BMI result
        if (bmi > 0) {
            Text(
                text = "Your BMI is: %.2f".format(bmi),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            Text(
                text = "Please enter valid height and weight",
                fontSize = 16.sp,
                color = androidx.compose.ui.graphics.Color.Red
            )
        }
    }
}