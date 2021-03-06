package com.example.navigation_compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.feature_random_photos.navigation.SharedScreen.PhotosScreen
import com.example.navigation.SharedScreen.BottomSheetScreen
import com.example.navigation.SharedScreen.ShowPostsScreen
import com.example.navigation_compose.destinations.ScreenA
import com.example.navigation_compose.destinations.ScreenB

class HomeScreen : Screen {
  @OptIn(ExperimentalMaterialApi::class)
  @Preview
  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    val photosScreen = rememberScreen(provider = PhotosScreen)
    val bottomSheetScreen = rememberScreen(provider = BottomSheetScreen)
    val postsScreen = rememberScreen(provider = ShowPostsScreen)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
      NavigateToDestinationButton("A",navigator,"I am at destination A")
      Spacer(modifier = Modifier.size(24.dp))
      NavigateToDestinationButton("B", navigator, "I am at destination B")
      Spacer(modifier = Modifier.size(24.dp))
      NavigateToDestinationButton("C", navigator, "I am at destination C")
      Spacer(modifier = Modifier.size(24.dp))
      NavigateToDestinationButton("D", navigator, "I am at destination D")
      Button(onClick = {
        navigator.push(photosScreen)
      }) {
        Text(text = "Navigate to Photos Screen")
      }
      Button(onClick = {
        navigator.push(postsScreen)
      }) {
        Text(text = "Navigate to posts Screen")
      }
      BottomSheetNavigator(scrimColor = Color.Transparent) {
        Navigator(screen = bottomSheetScreen)
      }
    }
  }

  @Composable
  fun NavigateToDestinationButton(
    destination: String,
    navigator: Navigator?,
    displayDestination: String
  ) {
    val endDestination = when(destination){
      "A" -> ScreenA(displayDestination)
      "B" -> ScreenB
      else -> ScreenB
    }
    Button(onClick = {
      navigator?.push(endDestination)
    }) {
        Text(text = destination)
    }
  }
}