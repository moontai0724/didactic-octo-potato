package tw.edu.pu.s1071530.putour.data

import tw.edu.pu.s1071530.putour.data.model.User

class LoginRepository(val dataSource: LoginDataSource) {
    var user: User? = null
        private set

    fun logout() {
        user = null
    }

    fun login(username: String, password: String): Result<User> {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
    }
}