package ru.kheynov.santa.login.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.kheynov.santa.R
import ru.kheynov.santa.core_ui.LocalSpacing
import ru.kheynov.santa.core_ui.theme.AccentColor

@Composable
fun MainLoginScreen(
    viewModel: MainLoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateBack: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    
    LaunchedEffect(true) {
        viewModel.actions.collect { action ->
            when (action) {
                MainLoginViewModel.Action.NavigateBack -> navigateBack()
                is MainLoginViewModel.Action.ShowError -> {
                    Toast.makeText(context, action.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        if (state.value is MainLoginViewModel.State.Idle) {
            item {
                TextField(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(vertical = spacing.spaceSmall),
                    value = (state.value as MainLoginViewModel.State.Idle).emailField,
                    onValueChange = { viewModel.updateEmailField(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text(text = stringResource(R.string.email)) },
                    placeholder = { Text(text = stringResource(R.string.enter_email_placeholder)) }
                )
                TextField(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(vertical = spacing.spaceSmall),
                    value = (state.value as MainLoginViewModel.State.Idle).passwordField,
                    onValueChange = { viewModel.updatePasswordField(it) },
                    label = { Text(text = stringResource(R.string.password)) },
                    placeholder = { Text(text = stringResource(R.string.enter_password_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
                
                ClickableText(
                    text = AnnotatedString(
                        context.getString(R.string.sign_up_button_text)
                    ),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = AccentColor,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = { navigateToRegister() },
                    modifier = Modifier.padding(
                        top = spacing.spaceExtraSmall,
                        bottom = spacing.spaceMedium
                    )
                )
                
                Button(
                    onClick = { viewModel.loginViaEmail() },
                    enabled = (state.value as MainLoginViewModel.State.Idle).loginButtonEnabled,
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login_button_text), style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }
                
            }
        } else {
            item {
                CircularProgressIndicator()
            }
        }
    }
}
