package tw.edu.pu.s1071530.putour.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.edu.pu.s1071530.putour.data.LoginRepository
import tw.edu.pu.s1071530.putour.data.Result

import tw.edu.pu.s1071530.putour.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value = LoginResult(success = true)
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (username == "") {
            _loginForm.value = LoginFormState(accountError = R.string.input_empty)
        } else if (password == "") {
            _loginForm.value = LoginFormState(passwordError = R.string.input_empty)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }
}
