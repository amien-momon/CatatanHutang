package com.momon.catatanhutang.repository

import com.momon.catatanhutang.data.UserDao
import com.momon.catatanhutang.model.Hutang
import com.momon.catatanhutang.model.User
import kotlinx.coroutines.flow.Flow

class RepositoryUser(private val userDao: UserDao) {

    //user
   suspend fun addUser(user: User){
        userDao.adduser(user)
    }

    fun readAllUser(): Flow<List<User>>{
        return userDao.readAllData()
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

    //hutang

    suspend fun addHutang(hutang: Hutang){
        userDao.addhutang(hutang)
    }

    fun readAllHutang(id_user:Int): Flow<List<Hutang>>{
        return userDao.readAllDataH(id_user)
    }
    suspend fun deleteAllHutang(id_user: Int){
        userDao.deleteAllHutang(id_user)
    }
    suspend fun deleteAllHutang1(){
        userDao.deleteAllHutang1()
    }
    suspend fun deleteHutang(hutang: Hutang){
        userDao.deleteHutang(hutang)
    }
    suspend fun updateHutang(hutang: Hutang){
        userDao.updateHutang(hutang)
    }



}