package com.example.StudyAppRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Kotlin : AppCompatActivity() {
    lateinit var list: List<KotlinNote>
    lateinit var myRv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setTitle("Kotlin Review")
        var btnAdd = findViewById<Button>(R.id.button3)
        list = listOf<KotlinNote>()

        btnAdd.setOnClickListener {
            val dialogBuilder = android.app.AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_dialog, null)
            dialogBuilder.setTitle("Alert Dialog")
            dialogBuilder.setView(dialogView)
            val edtitle = dialogView.findViewById<EditText>(R.id.edtitle1)
            val edmore = dialogView.findViewById<EditText>(R.id.edmore1)
            val eddes = dialogView.findViewById<EditText>(R.id.eddes1)
            val tvBtn = dialogView.findViewById<Button>(R.id.button6)
            tvBtn.setOnClickListener {
                var title =edtitle.text.toString()
                var expl =edmore.text.toString()
                var des =eddes.text.toString()
                val s = KotlinNote(0, title, expl, des)
                CoroutineScope(Dispatchers.IO).launch {
                    NoteDatabase.getInstance(applicationContext).KNoteDao().insertKNote(s)
                }
                Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                    .show()
                //retrieve data and update recycler view
                myRv()
            }
            dialogBuilder.show()
        }
        myRv = findViewById<RecyclerView>(R.id.myRv)
        myRv()
    }

    fun update(ID: Int){
        val dialogBuilder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setTitle("Alert Dialog")
        dialogBuilder.setView(dialogView)
        val edtitle = dialogView.findViewById<EditText>(R.id.edtitle2)
        val edmore = dialogView.findViewById<EditText>(R.id.edmore2)
        val eddes = dialogView.findViewById<EditText>(R.id.eddes2)
        val tvBtn = dialogView.findViewById<Button>(R.id.button7)
        tvBtn.setOnClickListener {
            var title =edtitle.text.toString()
            var expl =edmore.text.toString()
            var des =eddes.text.toString()
            val s = KotlinNote(ID, title, expl, des)
            CoroutineScope(Dispatchers.IO).launch {
                NoteDatabase.getInstance(applicationContext).KNoteDao().updateKNote(s)
            }
            Toast.makeText(applicationContext, "data updated successfully! ", Toast.LENGTH_SHORT)
                .show()
            println("updated item")
            //retrieve data and update recycler view
            myRv()
        }
        dialogBuilder.show()
    }


    fun delete(ID : Int){
        val s = KotlinNote(ID,"","","")
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).KNoteDao().deleteKNote(s)
        }
        Toast.makeText(applicationContext, "data deleted successfully! ", Toast.LENGTH_SHORT)
            .show()
        println("deleted item")
        //retrieve data and update recycler view
        myRv()
    }
    fun myRv(){
        CoroutineScope(Dispatchers.IO).launch {
            list = NoteDatabase.getInstance(applicationContext).KNoteDao()
                .getKNote()
            withContext(Main){
                //recycler view
                myRv.adapter = RecyclerViewAdapter2(this@Kotlin, list)
                myRv.layoutManager = LinearLayoutManager(this@Kotlin)
            }
        }

    }
}