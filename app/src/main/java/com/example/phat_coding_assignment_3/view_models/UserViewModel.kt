package com.example.phat_coding_assignment_3.view_models

import android.util.Log
import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.user.User
import com.example.phat_coding_assignment_3.data.user.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    /*
    *   Initialize data for User
    * */
    fun initializeUsers() {
        addNewUser()
    }

    // Cache all users
    val allUsers: LiveData<List<User>> = userDao.getUsers().asLiveData()

    /*
    * Add new User to database
    * */
    private fun addNewUser() {
        val newUser = getNewUserEntry()
        insertUser(newUser)
    }

    private fun insertUser(user: User) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    /*
    *  Create User Entry
    * */
    private fun getNewUserEntry(): User {
        return User()
    }

    /*
    * Add new User to database
    * */
    fun updateUser(id: Int, money: Int) {
        val updatedUser = getUpdatedUserEntry(id, money)
        editUser(updatedUser)
    }

    private fun editUser(user: User) {
        viewModelScope.launch {
            userDao.update(user)
        }
    }

    /*
    *  Create Land Entry
    * */
    private fun getUpdatedUserEntry(id: Int, money: Int): User {
        return User(
            id = id,
            money = money
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}