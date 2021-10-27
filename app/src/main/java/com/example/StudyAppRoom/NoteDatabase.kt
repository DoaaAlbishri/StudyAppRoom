package com.example.StudyAppRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [AndroidNote::class,KotlinNote::class],version = 1,exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

    companion object{
        var instance:NoteDatabase?=null;
        fun getInstance(ctx: Context):NoteDatabase
        {
            if(instance!=null)
            {
                return  instance as NoteDatabase;
            }
            instance= Room.databaseBuilder(ctx,NoteDatabase::class.java,"details").run { allowMainThreadQueries() }.build();
            return instance as NoteDatabase;
        }
    }
    abstract fun NoteDao():NoteDao;
    abstract fun KNoteDao():KNoteDao;

}