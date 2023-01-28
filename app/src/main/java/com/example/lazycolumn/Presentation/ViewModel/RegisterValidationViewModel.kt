package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.Domain.Use_Cases.*
import com.example.lazycolumn.FormValidation.LoginFormEvent
import com.example.lazycolumn.FormValidation.LoginValidationFormState
import com.example.lazycolumn.FormValidation.RegisterFormEvent
import com.example.lazycolumn.FormValidation.RegisterValidationFormState

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterValidationViewModel(private val validateEmail: ValidateEmail = ValidateEmail(), private val validateUserName: ValidateUserName = ValidateUserName(), private val validatePhone: ValidatePhone = ValidatePhone(), private val validateCityName: ValidateCityName = ValidateCityName(), private val validatePassword: ValidatePassword = ValidatePassword()):ViewModel() {
    var state by mutableStateOf(RegisterValidationFormState())
    private val validationeventchannel= Channel<ValidationEvent>()
    val validationEvents=validationeventchannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent){
        when(event){
            is RegisterFormEvent.EmailChanged->{
                state=state.copy(email = event.email)
            }
            is RegisterFormEvent.UserNameChanged->{
                state=state.copy(username = event.username)
            }
            is RegisterFormEvent.PhoneChanged->{
                state=state.copy(phone = event.phone)
            }
            is RegisterFormEvent.CityChanged->{
                state=state.copy(city = event.city)
            }
            is RegisterFormEvent.PasswordChanged->{
                state=state.copy(password = event.password)
            }
            is RegisterFormEvent.Register->{
                RegisterValidated()
            }
        }
    }
    private fun RegisterValidated() {
        val emailresult=validateEmail.execute(state.email)
        val usernameresult=validateUserName.execute(state.username)
        val phoneresult=validatePhone.execute(state.phone)
        val cityresult=validateCityName.execute(state.city)
        val passwordresult=validatePassword.execute(state.password)
        val haserror= listOf(emailresult,usernameresult,phoneresult,cityresult,passwordresult).any { !it.successfull }

        if (haserror){
            state=state.copy(
                emailError = emailresult.ErrorMessage,
                usernameError = usernameresult.ErrorMessage,
                phoneError = phoneresult.ErrorMessage,
                cityError = cityresult.ErrorMessage,
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