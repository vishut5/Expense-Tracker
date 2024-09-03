package com.vishu.expensetracker.feature.settings

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.vishu.expensetracker.R
import com.vishu.expensetracker.ui.theme.Green
import com.vishu.expensetracker.widget.ExpenseTextView

@Composable
fun SettingsScreen(navController: NavController) {
    val darkModeEnabled = remember { mutableStateOf(false) }
    val showPermissionMessage = remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Dialog to inform the user about dark mode and navigate to system settings
    if (showPermissionMessage.value) {
        PermissionMessageDialog(
            onDismiss = { showPermissionMessage.value = false },
            onOpenSettings = {
                // Navigate to the system settings screen for display options
                val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
                context.startActivity(intent) // Use the context directly
            }
        )
    }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigateUp() // Navigate back to the previous screen
                        },
                    colorFilter = ColorFilter.tint(Color.Black)
                )
                ExpenseTextView(
                    text = "Settings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Top
        ) {

            val notificationsEnabled = remember { mutableStateOf(true) }
            SettingItem(
                title = "Enable Notifications",
                onToggle = { notificationsEnabled.value = it },
                isChecked = notificationsEnabled.value
            )

            Divider(color = Color.LightGray)

            // Example setting: Dark Mode
            val darkModeEnabled = remember { mutableStateOf(false) }
            SettingItem(
                title = "Dark Mode",
                onToggle = { darkModeEnabled.value = it
                             showPermissionMessage.value = true
                           },
                isChecked = darkModeEnabled.value
            )

            Divider(color = Color.LightGray)

            // Example setting: Account
            SettingNavigationItem(
                title = "Account",
                onClick = {
                    // Navigate to account settings screen
                    navController.navigate("/profile")
                }
            )

            Divider(color = Color.LightGray)

            // Example setting: About
            SettingNavigationItem(
                title = "About",
                onClick = {
                    // Navigate to about screen
                    navController.navigate("/about")
                }
            )
        }
    }
}

@Composable
fun PermissionMessageDialog(onDismiss: () -> Unit, onOpenSettings: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Dark Mode") },
        text = {
            Text(
                "This app can change its own theme to dark mode but cannot modify the system-wide dark mode settings. " +
                        "Please go to your device's settings to " +
                        "adjust the system-wide dark mode if needed."



            )
        },
        confirmButton = {
            Button(onClick = {
                onOpenSettings()
                onDismiss()
            }) {
                Text("Open Settings")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun SettingItem(title: String, isChecked: Boolean, onToggle: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle(!isChecked) }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Switch(
            checked = isChecked,
            onCheckedChange = { onToggle(it) },
            colors = SwitchDefaults.colors(checkedThumbColor = Green)
        )
    }
}

@Composable
fun SettingNavigationItem(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
       Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutSection(navController: NavController) {
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                        navController.navigateUp() // Navigate back to the previous screen
                    },
                colorFilter = ColorFilter.tint(Color.Black)
            )
            ExpenseTextView(
                text = "About",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Terms & Conditions",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Text(
                    text = "Introduction:\nThese terms and conditions outline the rules and regulations for the use of our mobile application.\n\n" +
                            "License:\nBy using our app, you agree to comply with all applicable laws and regulations. The app is licensed to you, not sold.\n\n" +
                            "User Responsibilities:\nYou are responsible for maintaining the confidentiality of your account and password, and for restricting access to your device.\n\n" +
                            "Prohibited Activities:\nYou agree not to misuse the app or engage in any unlawful or prohibited activities.\n\n" +
                            "Limitation of Liability:\nWe are not liable for any damages arising from the use or inability to use the app.\n\n" +
                            "Modifications:\nWe reserve the right to modify or discontinue the app at any time without prior notice.\n\n" +
                            "Governing Law:\nThese terms are governed by the laws of India/U.P.\n\n" +
                            "Acceptance:\nBy using this app, you signify your acceptance of these terms and conditions. If you do not agree, please do not use the app.",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                Divider(color = Color.LightGray, thickness = 1.dp)
            }

            item {
                Text(
                    text = "Contact Us",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Text(
                    text = "Email: support@expensetracker.com\n" +
                            "Phone: +917895484641\n" +
                            "Address: Kila Parikshit Garh, Meerut",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}