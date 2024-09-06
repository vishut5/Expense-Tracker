package com.vishu.expensetracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vishu.expensetracker.feature.IntroScreen
import com.vishu.expensetracker.feature.SignInScreen
import com.vishu.expensetracker.feature.SignUpScreen
import com.vishu.expensetracker.feature.add_expense.AddExpense
import com.vishu.expensetracker.feature.home.HomeScreen
import com.vishu.expensetracker.feature.profile.ProfileScreen
import com.vishu.expensetracker.feature.settings.AboutSection
import com.vishu.expensetracker.feature.settings.SettingsScreen
import com.vishu.expensetracker.feature.stats.StatsScreen
import com.vishu.expensetracker.feature.transactionlist.TransactionListScreen
import com.vishu.expensetracker.ui.theme.Zinc

@Composable
fun NavHostScreen(authViewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var bottomBarVisibility by remember {
        mutableStateOf(true)}
        val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate("/home") {

                }
            }

            is AuthState.Error -> {
                // Handle any error state if needed
            }

            else -> {
                navController.navigate("/intro") {

                }
            }
        }
    }



    Scaffold(bottomBar = {
        AnimatedVisibility(visible = bottomBarVisibility) {
            NavigationBottomBar(
                navController = navController,
                items = listOf(
                    NavItem(route = "/home", icon = R.drawable.ic_home),
                    NavItem(route = "/stats", icon = R.drawable.ic_stats),

                    )
            )
        }
    }) {
        NavHost(
            navController = navController,
            startDestination = "/intro",
            modifier = Modifier.padding(it)
        ) {
            composable(route = "/intro") {
                bottomBarVisibility =false
                IntroScreen(navController)
            }
            composable(route = "/home") {
                bottomBarVisibility = true
                HomeScreen(navController)
            }

            composable(route = "/add_income") {
                bottomBarVisibility = false
                AddExpense(navController, isIncome = true)
            }
            composable(route = "/add_exp") {
                bottomBarVisibility = false
                AddExpense(navController, isIncome = false)
            }

            composable(route = "/stats") {
                bottomBarVisibility = true
                StatsScreen(navController)
            }
            composable(route = "/all_transactions") {
                bottomBarVisibility = true // Show the bottom bar if you want it visible
                TransactionListScreen(navController)
            }
            composable(route = "/profile") {
                bottomBarVisibility = false // Hide the bottom bar on profile screen
                ProfileScreen(navController)
            }
            composable(route = "/setting") {
                bottomBarVisibility = false // Hide the bottom bar on profile screen
               SettingsScreen(navController )
            }
            composable(route = "/about") {
                bottomBarVisibility = false // Hide the bottom bar on profile screen
                AboutSection(navController )
            }
            composable(route = "/signin") {
                bottomBarVisibility = false // Hide the bottom bar on profile screen
                SignInScreen(navController )
            }
            composable(route = "/signup") {
                bottomBarVisibility = false // Hide the bottom bar on profile screen
                SignUpScreen(navController )
            }
        }
    }
}


data class NavItem(
    val route: String,
    val icon: Int,
)

@Composable
fun NavigationBottomBar(
    navController: NavController,
    items: List<NavItem>,
) {
    // Bottom Navigation Bar
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Zinc,
                    selectedIconColor = Zinc,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}