package com.momon.catatanhutang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.momon.catatanhutang.data.UserDb
import com.momon.catatanhutang.model.Hutang
import com.momon.catatanhutang.repository.RepositoryUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HutangViewModel (application: Application): AndroidViewModel(application) {
    private val repositoryUser: RepositoryUser
    init {
        val userDao = UserDb.getDatabase(application).userDao()
        repositoryUser = RepositoryUser(userDao)
    }

    fun readAllData(id_user : Int): LiveData<List<Hutang>>{
           return repositoryUser.readAllHutang(id_user).asLiveData()

    }

    fun addHutang(hutang : Hutang){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.addHutang(hutang)
        }

    }
    fun updateHutang(hutang: Hutang){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.updateHutang(hutang)
        }
    }

    fun deleteAllHutang(id_user: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteAllHutang(id_user)
        }
    }
    fun deleteAllHutang1(){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteAllHutang1()
        }
    }
    fun deleteHutang(hutang: Hutang){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteHutang(hutang)
        }
    }
}