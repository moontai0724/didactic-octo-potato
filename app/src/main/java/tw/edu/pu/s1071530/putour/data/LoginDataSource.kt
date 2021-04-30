package tw.edu.pu.s1071530.putour.data

import tw.edu.pu.s1071530.putour.data.model.User
import java.io.IOException

class LoginDataSource {
    fun login(username: String, password: String): Result<User> {
        return try {
            if (username != "puapp" || password != "94168")
                throw Error("Invalid user")
            Result.Success(User(username, password))
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }
}