package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.FormValidation.LoginFormEvent
import com.example.lazycolumn.FormValidation.LoginValidationFormState
import com.example.lazycolumn.Use_Cases.ValidateEmail
import com.example.lazycolumn.Use_Cases.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginValidationViewModel(private val validateEmail:ValidateEmail= ValidateEmail(), private val validatePassword: ValidatePassword= ValidatePassword()):ViewModel() {


    var state by mutableStateOf(LoginValidationFormState())
    private val validationeventchannel=Channel<ValidationEvent>()
    val validationEvents=validationeventchannel.receiveAsFlow()
    fun onEvent(event: LoginFormEvent){
       when(event){
           is LoginFormEvent.EmailChanged->{
          state=state.copy(email = event.email)
           }
           is LoginFormEvent.PasswordChanged->{
               state=state.copy(password = event.password)
           }
           is LoginFormEvent.Login->{
               LoginValidated()
           }
       }
    }

    private fun LoginValidated() {
        val emailresult=validateEmail.execute(state.email)
        val passwordresult=validatePassword.execute(state.password)
        val haserror= listOf(emailresult,passwordresult).any { !it.successfull }

        if (haserror){
            state=state.copy(
                emailError = emailresult.ErrorMessage,
                passwordError = passwordresult.ErrorMessage
            )
            return
        }
        viewModelScope.launch {
        validationeventchannel.send(ValidationEvent.success)
        }
    }
sealed class ValidationEvent{
    object success: ValidationEvent()
}
}