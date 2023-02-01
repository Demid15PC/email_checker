package com.example.emailchecker.presentation

sealed class RegistrationFormEvent {
       data class EmailChanged(val email: String) : RegistrationFormEvent()
       data class PasswordChanged(val password: String) : RegistrationFormEvent()
       data class RepeatPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()
       data class AcceptedTearms(val isAccepted: Boolean) : RegistrationFormEvent()

       object Submid: RegistrationFormEvent()
}