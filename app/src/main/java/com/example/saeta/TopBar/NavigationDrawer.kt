package com.example.saeta.TopBar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.saeta.HomeScreen.HomeScreen
import com.example.saeta.R
import com.example.saeta.RutasScreen.RutasScreen
import com.example.saeta.SplashScreens
import com.example.saeta.ui.theme.primaryLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val items = listOf(
            NavigationItem(
                    title = "Inicio",
                    selectedIcon = R.drawable.icon_home,
                    screen = Screens.HomeScreen
            ),
            NavigationItem(
                    title = "Rutas",
                    selectedIcon = R.drawable.icon_route ,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Favoritos",
                    selectedIcon = R.drawable.icon_heart,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Lugares Turisticos",
                    selectedIcon = R.drawable.icon_building_monument,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Acerca De",
                    selectedIcon = R.drawable.icon_info_circle,
                    screen = Screens.HomeScreen
            )
    )
    Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        val navController = rememberNavController()
        val excludeRoutes = listOf(Screens.SplashScreen.route)
        val shouldShowTopBar: (String) -> Boolean = { route ->
            route !in excludeRoutes
        }
        val gesturesEnabled: (String) -> Boolean = { route ->
            route !in excludeRoutes
        }
        val configuration = LocalConfiguration.current
        val drawerWidthModifier = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Modifier.fillMaxWidth(0.85f)
        } else {
            Modifier
        }

        ModalNavigationDrawer(
                drawerContent = {
                                ModalDrawerSheet(modifier = drawerWidthModifier) {
                                    val scrollState= rememberScrollState()
                                    Column(modifier=Modifier.verticalScroll(scrollState)) {
                                        NavBarHeader()
                                        items.forEachIndexed { index, item ->
                                            NavigationDrawerItem(
                                                    label = { Text(text = item.title, color= primaryLight) },
                                                    selected = index == selectedItemIndex,
                                                    onClick = {

                                                        selectedItemIndex = index
                                                        scope.launch {
                                                            drawerState.close()
                                                        }
                                                        navController.navigate(item.screen.route) {
                                                            launchSingleTop = true
                                                        }
                                                    },
                                                    icon = {
                                                        Icon(painter = painterResource(id = item.selectedIcon), contentDescription = item.title, tint = primaryLight )
                                                    },
                                                    modifier = Modifier.padding(5.dp)
                                            )

                                        }
                                        Divider(
                                                modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(5.dp)
                                                        .height(2.dp),
                                                color = Color.Gray
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))

                                        NavigationDrawerItem(label = { Text("Cerrar App", color= primaryLight) },
                                                selected = false,
                                                onClick = {
                                                    scope.launch {
                                                        drawerState.close()
                                                    }
                                                    navController.popBackStack()

                                                },
                                                icon = {
                                                    Icon(painter = painterResource(id = R.drawable.icon_logout ) , contentDescription = null, tint = primaryLight)
                                                },
                                                modifier = Modifier.padding(5.dp))

                                    }
                                }
                },
                drawerState = drawerState,
                gesturesEnabled = navController.currentBackStackEntryAsState().value?.destination?.route?.let { gesturesEnabled(it) } == true
        ) {
            Scaffold(
                    topBar = {
                        val currentBackStackEntry = navController.currentBackStackEntryAsState()
                        if (currentBackStackEntry.value?.destination?.route?.let { shouldShowTopBar(it) } == true) {
                            TopApp(drawerState)
                        }
                    }
            ) { innerPadding ->
                NavHost(navController = navController,
                        startDestination = Screens.SplashScreen.route,
                        modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screens.SplashScreen.route) {
                        SplashScreens(navController = navController)
                    }
                    composable(Screens.HomeScreen.route) {
                        HomeScreen()
                    }
                    composable(Screens.RutasScreen.route) {
                        RutasScreen()
                    }

                }
            }

        }
    }
}
