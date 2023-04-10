package ru.kheynov.santa.auth.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.useCases.LoginViaEmailUseCase
import ru.kheynov.santa.core.util.Resource
import ru.kheynov.santa.coreUi.utils.UiText

@HiltViewModel
class MainLoginViewModel @Inject constructor(
    private val loginViaEmailUseCase: LoginViaEmailUseCase,
) : ViewModel() {

    internal sealed interface State {
        data class Idle(
            val emailField: String = "",
            val passwordField: String = "",
            val loginButtonEnabled: Boolean = false,
        ) : State

        object Loading : State
    }

    internal sealed interface Action {
        object NavigateBack : Action
        data class ShowError(val error: UiText) : Action
    }

    private val _actions = Channel<Action>(Channel.BUFFERED)
    internal val actions = _actions.receiveAsFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Idle())
    internal val state: StateFlow<State> = _state.asStateFlow()

    internal fun updateEmailField(input: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (_state.value is State.Idle) {
                _state.update { state ->
                    (state as State.Idle).copy(emailField = input).let {
                        if (it.emailField.isNotBlank() && it.passwordField.isNotBlank()) {
                            it.copy(loginButtonEnabled = true)
                        } else {
                            it
                        }
                    }
                }
            }
        }
    }

    internal fun updatePasswordField(input: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (_state.value is State.Idle) {
                _state.update { state ->
                    (state as State.Idle).copy(passwordField = input).let {
                        if (it.passwordField.isNotBlank() && it.emailField.isNotBlank()) {
                            it.copy(loginButtonEnabled = true)
                        } else {
                            it
                        }
                    }
                }
            }
        }
    }

    internal fun loginViaEmail() {
        val lastState = _state.value as State.Idle
        viewModelScope.launch(Dispatchers.Default) { _state.update { State.Loading } }
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginViaEmailUseCase.invoke(
                AuthDTO.LoginViaEmail(
                    email = lastState.emailField,
                    password = lastState.passwordField,
                ),
            )
            when (result) {
                is Resource.Failure -> {
                    _actions.send(Action.ShowError(UiText.PlainText(result.exception.toString())))
                }
                is Resource.Success -> {
                    _actions.send(Action.NavigateBack)
                }
            }
            withContext(Dispatchers.Default) {
                _state.update { lastState }
            }
        }
    }
}