package com.momon.catatanhutang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.momon.catatanhutang.data.UserDb
import com.momon.catatanhutang.model.User
import com.momon.catatanhutang.repository.RepositoryUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application): AndroidViewModel(application){
    private val repositoryUser: RepositoryUser
    init {
        val userDao = UserDb.getDatabase(application).userDao()
        repositoryUser = RepositoryUser(userDao)
    }
    val readAllData = repositoryUser.readAllUser().asLiveData()

    fun addUser(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.updateUser(user)
        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteAllUser()
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteUser(user)
        }
    }
}