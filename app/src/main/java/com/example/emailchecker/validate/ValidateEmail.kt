package com.example.emailchecker.validate

import android.util.Patterns

class ValidateEmail {
       fun execute(email: String): ValidateResult {
              if (email.isBlank()){
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Поле не должно быть пустым"
                     )
              }
              if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Ваша почта не корректна"
                     )
              }
              return ValidateResult(
                     sucessful = true
              )
       }
}