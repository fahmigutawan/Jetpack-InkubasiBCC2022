package com.bcc.silomba

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcc.silomba.component.AppBottomMenu
import com.bcc.silomba.component.AppSnackbar
import com.bcc.silomba.navigation.AppNavRoute
import com.bcc.silomba.screen.CariLombaScreen
import com.bcc.silomba.screen.HomeScreen
import com.bcc.silomba.screen.LoginScreen
import com.bcc.silomba.screen.LombaDetailScreen
import com.bcc.silomba.screen.OnboardScreen
import com.bcc.silomba.screen.SplashScreen
import com.bcc.silomba.viewmodel.MainViewModel

@Composable
fun SilombaContent() {
    /**Attrs*/
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()

    /**Function*/
    navController.addOnDestinationChangedListener { _, destination, _ ->
        destination.route?.let {
            when (it) {
                AppNavRoute.HomeScreen.name -> {
                    mainViewModel.showBottomBar.value = true
                }

                AppNavRoute.LombaScreen.name -> {
                    mainViewModel.showBottomBar.value = true
                }

                AppNavRoute.PartnerScreen.name -> {
                     mainViewModel.showBottomBar.value = true
                }

                AppNavRoute.LayananScreen.name -> {
                    mainViewModel.showBottomBar.value = true
                }

                AppNavRoute.ProfilScreen.name -> {
                    mainViewModel.showBottomBar.value = true
                }

                else -> {
                    mainViewModel.showBottomBar.value = false
                }
            }
        }
    }
    if (mainViewModel.snackbarActive.value) {
        LaunchedEffect(key1 = true) {
            val resetSnackbarState = {
//                mainViewModel.apply {
//                    snackbarAction.value = {}
//                    snackbarActionLabel.value = "Tutup"
//                    snackbarActive.value = false
//                    snackbarMessage.value = ""
//                }

                mainViewModel.snackbarActive.value = false
            }
            val snackbarData = scaffoldState
                .snackbarHostState
                .showSnackbar(
                    message = mainViewModel.snackbarMessage.value,
                    actionLabel = mainViewModel.snackbarActionLabel.value,
                    duration = SnackbarDuration.Short
                )

            when (snackbarData) {
                SnackbarResult.Dismissed -> {
                    resetSnackbarState()
                }

                SnackbarResult.ActionPerformed -> {
                    when (mainViewModel.snackbarActionLabel.value) {
                        "Tutup" -> {
                            scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                            resetSnackbarState()
                        }

                        else -> {
                            mainViewModel.snackbarAction.value()
                            resetSnackbarState()
                        }
                    }
                }
            }
        }
    }

    /**Content*/
    Scaffold(
        bottomBar = {
            if (mainViewModel.showBottomBar.value) {
                AppBottomMenu(
                    bottomMenuState = mainViewModel.bottomMenuState,
                    onItemClicked = {
                        navController.navigate(route = it)
                    }
                )
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            AppSnackbar(hostState = it)
        }
    ) {
        SilombaNavHost(
            navController = navController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
private fun SilombaNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    //Attrs
    val showSnackbar: (message: String) -> Unit = { message ->
        mainViewModel.snackbarMessage.value = message
        mainViewModel.snackbarActive.value = true
    }
    val showSnackbarWithAction: (message: String, action: () -> Unit) -> Unit = { message, action ->
        mainViewModel.snackbarMessage.value = message
        mainViewModel.snackbarActive.value = true
        mainViewModel.snackbarAction.value = action
    }

    //Content
    NavHost(navController = navController, startDestination = AppNavRoute.SplashScreen.name) {
        composable(route = AppNavRoute.SplashScreen.name) {
            SplashScreen(
                onNavigateToOnboard = {
                    navController.navigate(route = AppNavRoute.OnboardScreen.name)
                },
                onNavigateToLogin = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                },
                onNavigateToHome = {
                    navController.navigate(route = AppNavRoute.HomeScreen.name)
                })
        }

        composable(route = AppNavRoute.OnboardScreen.name) {
            OnboardScreen(
                onPassClicked = {
                    navController.navigate(route = AppNavRoute.LoginScreen.name)
                }
            )
        }

        composable(route = AppNavRoute.LoginScreen.name) {
            LoginScreen(
                onLupaPasswordClicked = { /*TODO*/ },
                onNavigateToHome = {
                    navController.navigate(route = AppNavRoute.HomeScreen.name) {
                        popUpTo(route = AppNavRoute.LoginScreen.name) {
                            inclusive = true
                        }
                    }
                },
                showSnackbar = showSnackbar
            )
        }

        composable(route = AppNavRoute.RegisterScreen.name) {

        }

        composable(route = AppNavRoute.HomeScreen.name) {
            HomeScreen(
                mainViewModel = mainViewModel,
                onNavigateToDetailLomba = {
                    navController.navigate(route = AppNavRoute.LombaDetail.name)
                }
            )
        }

        composable(route = AppNavRoute.LombaScreen.name) {
            CariLombaScreen()
        }

        composable(route = AppNavRoute.PartnerScreen.name) {

        }

        composable(route = AppNavRoute.LayananScreen.name) {

        }

        composable(route = AppNavRoute.ProfilScreen.name) {

        }

        composable(route = AppNavRoute.LombaDetail.name) {
            mainViewModel.pickedLomba.value?.let {
                LombaDetailScreen(
                    onBackClicked = { navController.popBackStack() },
                    onKlikDisiniClicked = {},
                    lombaDetail = it
                )
            }
        }
    }
}