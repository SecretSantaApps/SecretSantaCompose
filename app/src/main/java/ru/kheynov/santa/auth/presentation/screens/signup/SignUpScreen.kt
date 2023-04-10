package ru.kheynov.santa.auth.presentation.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ru.kheynov.santa.coreUi.LocalSpacing

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
}