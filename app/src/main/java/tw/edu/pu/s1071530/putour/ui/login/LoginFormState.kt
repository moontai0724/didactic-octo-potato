package tw.edu.pu.s1071530.putour.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val accountError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false)