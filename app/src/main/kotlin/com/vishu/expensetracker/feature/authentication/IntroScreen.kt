package com.vishu.expensetracker.feature.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.vishu.expensetracker.R

val customFontFamily = FontFamily(
    Font(R.font.inter_black),
    Font(R.font.inter_regular)
)
val customFontFamily2 = FontFamily(
    Font(R.font.inter_medium)
)

@Composable
fun IntroScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "EXPENSE TRACKER",
                color = Color(0xFF25A969),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFontFamily,
                modifier = Modifier.padding(top = 32.dp),

                )
            Spacer(modifier = Modifier.height(50.dp))


            Image(
                painter = painterResource(id = R.drawable.introapp),
                contentDescription = "Intro Image",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(300.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Let's Get Started",
                color = Color(0xFF333333),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp),
                fontFamily = customFontFamily2
            )


            Text(
                text = "Track your expenses and manage your budget easily with our intuitive and minimalist interface.",
                color = Color(0xFF666666),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))


            Button(
                onClick = { navController.navigate("/signin") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25A969))
            ) {
                Text(
                    text = "SIGN IN",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = { navController.navigate("/signup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF25A969)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color(0xFF25A969),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

