package com.example.StudyAppRoom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Main Activity")

        var btn1 = findViewById<Button>(R.id.button)
        var btn2 = findViewById<Button>(R.id.button2)

        btn1.setOnClickListener {
            val intent = Intent(this, Kotlin::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, Android::class.java)
            startActivity(intent)
        }
    }
}