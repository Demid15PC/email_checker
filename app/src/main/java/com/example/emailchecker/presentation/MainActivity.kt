package com.example.emailchecker.presentation

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emailchecker.ui.theme.EmailCheckerTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setContent {
                     EmailCheckerTheme {
                            Surface(
                                   modifier = Modifier.fillMaxSize(),
                                   color = MaterialTheme.colors.background
                            ) {
                                   val viewModel = viewModel<MainViewModel>()
                                   val state = viewModel.state
                                   val context = LocalContext.current
                                   LaunchedEffect(key1 = context) {
                                          viewModel.validdationEvents.collect { event ->
                                                 when (event) {
                                                        is MainViewModel.ValidationEvent.Success -> {
                                                               Toast.makeText(
                                                                      context,
                                                                      "Регистрация прошла успешно",
                                                                      Toast.LENGTH_LONG,
                                                               ).show()
                                                        }
                                                 }
                                          }
                                   }
                                   Column(
                                          modifier = Modifier
                                                 .fillMaxSize()
                                                 .padding(32.dp),
                                          verticalArrangement = Arrangement.Center
                                   ) {


                                          TextField(
                                                 value = state.email,
                                                 onValueChange = {
                                                        viewModel.onEvent(
                                                               RegistrationFormEvent.EmailChanged(
                                                                      it
                                                               )
                                                        )
                                                 },
                                                 isError = state.emailError != null,
                                                 modifier = Modifier.fillMaxWidth(),
                                                 placeholder = {
                                                        Text(text = "Email")
                                                 },
                                                 keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Email
                                                 )
                                          )
                                          if (state.emailError != null) {
                                                 Text(
                                                        text = state.emailError,
                                                        color = MaterialTheme.colors.error
                                                 )
                                          }
                                          Spacer(modifier = Modifier.height(15.dp))


                                          TextField(
                                                 value = state.password,
                                                 onValueChange = {
                                                        viewModel.onEvent(
                                                               RegistrationFormEvent.PasswordChanged(
                                                                      it
                                                               )
                                                        )
                                                 },
                                                 isError = state.passwordError != null,
                                                 modifier = Modifier.fillMaxWidth(),
                                                 placeholder = {
                                                        Text(text = "Пароль")
                                                 },
                                                 keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Password
                                                 )
                                          )
                                          if (state.passwordError != null) {
                                                 Text(
                                                        text = state.passwordError,
                                                        color = MaterialTheme.colors.error
                                                 )
                                          }
                                          Spacer(modifier = Modifier.height(15.dp))


                                          TextField(
                                                 value = state.repeatedPassword,
                                                 onValueChange = {
                                                        viewModel.onEvent(
                                                               RegistrationFormEvent.RepeatPasswordChanged(
                                                                      it
                                                               )
                                                        )
                                                 },
                                                 isError = state.repeatedPasswordError != null,
                                                 modifier = Modifier.fillMaxWidth(),
                                                 placeholder = {
                                                        Text(text = "Email")
                                                 },
                                                 keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Email
                                                 )
                                          )
                                          if (state.repeatedPasswordError != null) {
                                                 Text(
                                                        text = state.repeatedPasswordError,
                                                        color = MaterialTheme.colors.error
                                                 )
                                          }
                                          Spacer(modifier = Modifier.height(15.dp))


                                          Row(
                                                 modifier = Modifier.fillMaxWidth()
                                          ) {
                                                 Checkbox(
                                                        checked = state.acceptedTerms,
                                                        onCheckedChange = {
                                                               viewModel.onEvent(
                                                                      RegistrationFormEvent.AcceptedTearms(
                                                                             it
                                                                      )
                                                               )
                                                        }
                                                 )
                                                 Spacer(modifier = Modifier.width(8.dp))
                                                 Text(text = "Принять условия")
                                          }
                                          if (state.termsError != null) {
                                                 Text(
                                                        text = state.termsError,
                                                        color = MaterialTheme.colors.error
                                                 )
                                          }

                                          Button(
                                                 onClick = { viewModel.onEvent(RegistrationFormEvent.Submid) },
                                                 modifier = Modifier.align(Alignment.End)
                                          ) {
                                                 Text(text = "Зарегестрироваться")
                                          }
                                   }
                            }
                     }
              }
       }
}
