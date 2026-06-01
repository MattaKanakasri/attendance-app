package com.example.attendanceapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var addBtn: Button
    private lateinit var markPresentBtn: Button
    private lateinit var markAbsentBtn: Button
    private lateinit var listView: ListView
    private lateinit var resultText: TextView

    private val students = ArrayList<String>()
    private val attendance = HashMap<String, String>()

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.nameInput)
        addBtn = findViewById(R.id.addBtn)
        markPresentBtn = findViewById(R.id.presentBtn)
        markAbsentBtn = findViewById(R.id.absentBtn)
        listView = findViewById(R.id.listView)
        resultText = findViewById(R.id.resultText)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        listView.adapter = adapter

        // Add student
        addBtn.setOnClickListener {
            val name = nameInput.text.toString()

            if (name.isNotEmpty()) {
                students.add(name)
                attendance[name] = "Not Marked"
                adapter.notifyDataSetChanged()
                nameInput.text.clear()
            } else {
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
            }
        }

        // Mark Present
        markPresentBtn.setOnClickListener {
            val name = nameInput.text.toString()
            if (students.contains(name)) {
                attendance[name] = "Present"
                resultText.text = "$name marked PRESENT"
            } else {
                resultText.text = "Student not found"
            }
        }

        // Mark Absent
        markAbsentBtn.setOnClickListener {
            val name = nameInput.text.toString()
            if (students.contains(name)) {
                attendance[name] = "Absent"
                resultText.text = "$name marked ABSENT"
            } else {
                resultText.text = "Student not found"
            }
        }
    }
}
