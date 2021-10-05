package com.momon.catatanhutang.data

import androidx.room.*
import com.momon.catatanhutang.model.Hutang
import com.momon.catatanhutang.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {


    //user Table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(vararg users: User)

    @Query("select * from user_table order by id asc")
    fun readAllData(): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun  deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    //hutang Table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addhutang(vararg hutang: Hutang)

    @Query("select * from hutang_table WHERE id_user = :id_user")
    fun readAllDataH(id_user: Int): Flow<List<Hutang>>

    @Query("DELETE FROM hutang_table WHERE id_user = :id_user ")
    suspend fun deleteAllHutang(id_user: Int)

    @Query("DELETE FROM hutang_table")
    suspend fun deleteAllHutang1()

    @Delete
    suspend fun  deleteHutang(hutang: Hutang )

    @Update
    suspend fun updateHutang(hutang: Hutang)




}