package com.example.a01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget. Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save.setOnClickListener {
            //รับข้อมูลจาก Edit Text
            val First = fst.text.toString()
            val Last = sur.text.toString()

//สร้าง Node สําหรับการเชื่อมต่อ Firebase Database
            val fb = FirebaseDatabase.getInstance()
            val ref = fb.getReference("Employee")
            val id:String? = ref.push().key//Save ID vaggiuna

            val Employee = Employee(id.toString(), First, Last)//สร้าง Object ในการเก็บข้อมูล
            ref.child(id.toString()).setValue(Employee).addOnCompleteListener {//เพิ่มข้อมูล firebase
                Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()
                fst.setText("")
                sur.setText("")
            }
        }
    }
}
