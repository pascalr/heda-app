package com.heda.helpers

import com.heda.helpers.Result
import com.heda.models.LoggedInUser
import java.io.IOException
import java.util.*

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository() {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        // TODO: handle logout for the service
    }

    fun login(username: String, password: String): Result<LoggedInUser> {

        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            setLoggedInUser(fakeUser)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}