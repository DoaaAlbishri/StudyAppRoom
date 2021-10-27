package com.example.StudyAppRoom

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Android : AppCompatActivity() {
    lateinit var list: List<AndroidNote>
    lateinit var myRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        setTitle("Android Review")
        var btnAdd = findViewById<Button>(R.id.button4)
        list = listOf<AndroidNote>()
        myRv = findViewById<RecyclerView>(R.id.myRv)

        myRv()

        btnAdd.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_dialog, null)
            dialogBuilder.setTitle("Alert Dialog")
            dialogBuilder.setView(dialogView)
            val edtitle = dialogView.findViewById<EditText>(R.id.edtitle1)
            val edmore = dialogView.findViewById<EditText>(R.id.edmore1)
            val eddes = dialogView.findViewById<EditText>(R.id.eddes1)
            val tvBtn = dialogView.findViewById<Button>(R.id.button6)
            tvBtn.setOnClickListener {
                var title = edtitle.text.toString()
                var expl = edmore.text.toString()
                var des = eddes.text.toString()
                val s = AndroidNote(0, title, expl, des)
                CoroutineScope(Dispatchers.IO).launch {
                    NoteDatabase.getInstance(applicationContext).NoteDao().insertNote(s)
                }
                Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                    .show()
                //retrieve data and update recycler view
                myRv()
            }
            dialogBuilder.show()
        }
    }

    fun update(ID:Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setTitle("Alert Dialog")
        dialogBuilder.setView(dialogView)
        val edtitle = dialogView.findViewById<EditText>(R.id.edtitle2)
        val edmore = dialogView.findViewById<EditText>(R.id.edmore2)
        val eddes = dialogView.findViewById<EditText>(R.id.eddes2)
        val tvBtn = dialogView.findViewById<Button>(R.id.button7)
        tvBtn.setOnClickListener {
            var title = edtitle.text.toString()
            var expl = edmore.text.toString()
            var des = eddes.text.toString()
            val s = AndroidNote(ID, title, expl, des)
            CoroutineScope(Dispatchers.IO).launch {
                NoteDatabase.getInstance(applicationContext).NoteDao().updateNote(s)
            }
            Toast.makeText(applicationContext, "data updated successfully! ", Toast.LENGTH_SHORT)
                .show()
            println("updated item")
            //retrieve data and update recycler view
            myRv()
        }
        dialogBuilder.show()
    }

    fun delete(ID: Int) {
        val s = AndroidNote(ID,"","","")
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).NoteDao().deleteNote(s)
        }
        Toast.makeText(applicationContext, "data deleted successfully! ", Toast.LENGTH_SHORT)
            .show()
        println("deleted item")
        //retrieve data and update recycler view
        myRv()
    }

    fun myRv() {
        CoroutineScope(Dispatchers.IO).launch {
            list = NoteDatabase.getInstance(applicationContext).NoteDao()
                .getNote()
            withContext(Dispatchers.Main){
                //recycler view
                myRv.adapter = RecyclerViewAdapter(this@Android, list)
                myRv.layoutManager = LinearLayoutManager(this@Android)
            }
        }
    }
}