package com.example.emailchecker.validate

class ValidateTearms {
       fun execute(isTearms: Boolean): ValidateResult {
              if (!isTearms) {
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Пожалуйста примите условия"
                     )
              }
              return ValidateResult(
                     sucessful = true
              )
       }
}