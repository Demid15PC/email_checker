package com.example.emailchecker.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emailchecker.validate.ValidateEmail
import com.example.emailchecker.validate.ValidatePassword
import com.example.emailchecker.validate.ValidateRepeatPassword
import com.example.emailchecker.validate.ValidateTearms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
       private val validateEmail: ValidateEmail = ValidateEmail(),
       private val validatePassword: ValidatePassword = ValidatePassword(),
       private val validateRepeatPassword: ValidateRepeatPassword = ValidateRepeatPassword(),
       private val validateTearms: ValidateTearms = ValidateTearms()
) : ViewModel() {
       var state by mutableStateOf(RegistrationFormState())

       private val validationEventChanel = Channel<ValidationEvent>()
       val validdationEvents = validationEventChanel.receiveAsFlow()

       fun onEvent(event: RegistrationFormEvent) {
              when (event) {
                     is RegistrationFormEvent.EmailChanged -> {
                            state = state.copy(email = event.email)
                     }
                     is RegistrationFormEvent.PasswordChanged -> {
                            state = state.copy(password = event.password)
                     }
                     is RegistrationFormEvent.RepeatPasswordChanged -> {
                            state = state.copy(repeatedPassword = event.repeatedPassword)
                     }
                     is RegistrationFormEvent.AcceptedTearms -> {
                            state = state.copy(acceptedTerms = event.isAccepted)
                     }
                     is RegistrationFormEvent.Submid -> {
                            submitData()
                     }
              }
       }

       private fun submitData() {
              val emailResult = validateEmail.execute(state.email)
              val passwordResult = validatePassword.execute(state.password)
              val repeatPasswordResult = validateRepeatPassword.execute(
                     state.password,
                     state.repeatedPassword
              )
              val termsResult = validateTearms.execute(state.acceptedTerms)

              val hasError = listOf(
                     emailResult,
                     passwordResult,
                     repeatPasswordResult,
                     termsResult
              ).any { !it.sucessful }

              if (hasError) {
                     state = state.copy(
                            emailError = emailResult.errorMessage,
                            passwordError = passwordResult.errorMessage,
                            repeatedPasswordError = repeatPasswordResult.errorMessage,
                            termsError = termsResult.errorMessage
                     )
                     return
              }
              viewModelScope.launch{
                     validationEventChanel.send(ValidationEvent.Success)
              }
       }
       sealed class ValidationEvent {
              object Success: ValidationEvent()
       }
}