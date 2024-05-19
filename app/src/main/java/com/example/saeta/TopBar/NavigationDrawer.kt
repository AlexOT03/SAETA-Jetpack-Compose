package com.example.saeta.TopBar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saeta.HomeScreen.HomeScreen
import com.example.saeta.LoginScreen.LoginScreen
import com.example.saeta.RutasScreen.RutasScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(){
    val items = listOf(
            NavigationItem(
                    title = "Inicio",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    screen = Screens.HomeScreen
            ),
            NavigationItem(
                    title = "Rutas",
                    selectedIcon = Icons.Filled.Place,
                    unselectedIcon = Icons.Outlined.Place,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Favoritos",
                    selectedIcon = Icons.Filled.Favorite,
                    unselectedIcon = Icons.Outlined.FavoriteBorder,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Lugares Turisticos",
                    selectedIcon = Icons.Filled.Notifications,
                    unselectedIcon = Icons.Outlined.Notifications,
                    screen = Screens.RutasScreen
            ),
            NavigationItem(
                    title = "Acerca De",
                    selectedIcon = Icons.Filled.Info,
                    unselectedIcon = Icons.Outlined.Info,
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
        ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.75f)) {
                        NavBarHeader()
                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                    label = { Text(text = item.title) },
                                    selected = index == selectedItemIndex,
                                    onClick = {

                                        selectedItemIndex = index
                                        scope.launch {
                                            drawerState.close()
                                        }
                                        navController.navigate(item.screen.route){
                                            launchSingleTop=true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = null)
                                    },
                                    modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                },
                drawerState = drawerState,
                gesturesEnabled = true
        ) {
            Scaffold(
                    topBar = {
                        TopAppBar(
                                title = {
                                    Text(text = "")
                                }, navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                        })
                    }
            ) {innerPadding ->
                NavHost(navController = navController,
                        startDestination = Screens.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                ){
                    composable(Screens.HomeScreen.route){
                        HomeScreen()
                    }
                    composable(Screens.RutasScreen.route){
                        RutasScreen()
                    }
                }
            }

        }
    }
}
