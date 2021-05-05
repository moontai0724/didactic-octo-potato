package tw.edu.pu.s1071530.putour.data.login

import tw.edu.pu.s1071530.putour.data.login.model.User
import java.io.IOException

class LoginDataSource {
    fun login(username: String, password: String): Result<User> {
        return try {
            if ((username == "puapp" && password == "94168") || (username == "puuser" && password == "54168"))
                Result.Success(User(username, password))
            else
                throw Error("Invalid user")
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }
}