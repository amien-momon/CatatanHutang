package com.momon.catatanhutang.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "hutang_table")
data class Hutang(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val id_user: Int,
    val hutang : String,
    val tgl : String,
    val jmlh : String
):Parcelable
