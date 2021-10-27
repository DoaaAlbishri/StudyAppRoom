package com.example.StudyAppRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AndroidNote")
data class AndroidNote(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID") val ID: Int,
  //  @ColumnInfo(name = "type")
  //  val type: String,
    @ColumnInfo(name = "title")
    val title :String,
    @ColumnInfo(name = "More")
    val More:String,
    @ColumnInfo(name = "des")
    val des: String
    )