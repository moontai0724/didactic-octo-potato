package tw.edu.pu.s1071530.putour.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: Boolean? = null,
        val error: Int? = null
)