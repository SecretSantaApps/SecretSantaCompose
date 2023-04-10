package ru.kheynov.santa.auth.presentation.screens.signup

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
import ru.kheynov.santa.R
import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.useCases.SignUpViaEmailUseCase
import ru.kheynov.santa.coreUi.utils.UiText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpViaEmailUseCase: SignUpViaEmailUseCase,
) : ViewModel() {

    sealed interface Action {
        object NavigateBack : Action
        data class ShowError(val error: UiText) : Action
    }

    sealed interface State {
        data class Idle(
            val loginField: String = "",
            val username: String = "",
            val passwordField: String = "",
            val repeatPassword: String = "",
            val address: String = "",
            val avatar: Int = 1,
        ) : State

        object Loading : State
    }

    private val _actions = Channel<Action>(Channel.BUFFERED)
    internal val actions = _actions.receiveAsFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Idle())
    internal val state: StateFlow<State> = _state.asStateFlow()

    internal fun updateState(state: State.Idle) {
        if (_state.value !is State.Idle) return
        _state.value = state
    }

    internal fun signUpViaEmail() {
        val lastState = if (state.value is State.Idle) state.value as State.Idle else return
        if (lastState.repeatPassword != lastState.passwordField) {
            _actions.trySend(Action.ShowError(UiText.StringResource(R.string.passwords_not_match)))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _state.update { State.Loading }
            }
            signUpViaEmailUseCase.invoke(
                AuthDTO.SignUpViaEmail(
                    username = lastState.username,
                    email = lastState.loginField,
                    password = lastState.passwordField,
                    address = lastState.address,
                    avatar = lastState.avatar,
                ),
            )
        }
    }
}