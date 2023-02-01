package com.example.emailchecker.validate

class ValidatePassword {
       fun execute(password: String): ValidateResult {
              if (password.length < 8) {
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Пароль должен быть больше 8 символов"
                     )
              }
              val containLettersAndDigits = password.any(){ it.isDigit() } && password.any(){ it.isLetter() }
              if (!containLettersAndDigits) {
                     return ValidateResult(
                            sucessful = false,
                            errorMessage = "Ваш пароль должен содержать буквы и цифры"
                     )
              }
              return ValidateResult(
                     sucessful = true
              )
       }
}
