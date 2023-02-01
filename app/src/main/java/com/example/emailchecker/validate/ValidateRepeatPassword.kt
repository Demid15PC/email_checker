package com.example.emailchecker.validate

class ValidateRepeatPassword {
       fun execute(password: String, repeatPassword: String): ValidateResult {
              if (password != repeatPassword) {
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Пароли должны совпадать"
                     )
              }
              return ValidateResult(
                     sucessful = true
              )
       }
}