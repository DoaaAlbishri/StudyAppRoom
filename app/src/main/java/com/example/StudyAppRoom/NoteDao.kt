package com.example.StudyAppRoom

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM AndroidNote")
    fun getNote(): List<AndroidNote>

    @Insert
    fun insertNote(note: AndroidNote)

    @Delete
    fun deleteNote(note: AndroidNote)

    @Update
    fun updateNote(note: AndroidNote)
}
    @Dao
    interface KNoteDao {
    @Query("SELECT * FROM KotlinNote")
    fun getKNote(): List<KotlinNote>

    @Insert
    fun insertKNote(note: KotlinNote)

    @Delete
    fun deleteKNote(note: KotlinNote)

    @Update
    fun updateKNote(note: KotlinNote)

}