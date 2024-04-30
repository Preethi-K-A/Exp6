package com.example.exp6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt1 : EditText = findViewById(R.id.tv1)
        val txt2 : EditText = findViewById(R.id.tv2)
        val txt3 : EditText = findViewById(R.id.tv3)
        val save : Button = findViewById(R.id.bt1)
        val load : Button = findViewById(R.id.bt2)

        save.setOnClickListener {
            val reg = txt1.text.toString()
            val name = txt2.text.toString()
            val cgpa = txt3.text.toString()

            val file = File(getExternalFilesDir(null),"student.txt")
            val fos = FileOutputStream(file)
            fos.write("$reg,$name,$cgpa\n".toByteArray())
            txt1.text.clear()
            txt2.text.clear()
            txt3.text.clear()
            fos.close()
        }
        load.setOnClickListener {
            val file = File(getExternalFilesDir(null),"student.txt")
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            var line : String
            line = br.readLine()
            var parts = line.split(",")
            val reg =  parts[0]
            val name = parts[1]
            val cgpa = parts[2]
            txt1.setText(reg)
            txt2.setText(name)
            txt3.setText(cgpa)
        }
    }
}