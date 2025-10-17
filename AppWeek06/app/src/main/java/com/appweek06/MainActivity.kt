package com.appweek06

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //UI component
    private lateinit var buttonAdd: Button
    private lateinit var buttonClear: Button
    private lateinit var listView: ListView
    private lateinit var editTextStudent: EditText
    private lateinit var textViewCount: TextView

    //Collection
    private lateinit var studentList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    //adapter : 서로 다른 객체를 연결 시켜주는 매개체

    companion object{
        private const val TAG="KotlinWeek06App"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate:Appwekk06 started")

        setupViews()
        setupListView()
        setupListeners()

        addInitialData()
    }

    private fun setupViews(){
        listView=findViewById(R.id.listViewStudents)
        editTextStudent = findViewById(R.id.editTextStudent)
        buttonClear=findViewById(R.id.buttonClear)
        buttonAdd =findViewById(R.id.buttonAdd)
        textViewCount=findViewById(R.id.textViewCount)
        //findviewById: id 값을 통해 바인딩

        studentList =ArrayList()
        Log.d(TAG, "Views initialized")

    }
    private fun setupListView(){
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, studentList)
        listView.adapter=adapter
        Log.d(TAG, "ListViews and Adapter setup completed")
    }
    private fun setupListeners(){
        buttonAdd.setOnClickListener{
            addStudent()
        }
        buttonClear.setOnClickListener {
            clearAllstudents()
        }
        //삭제
        listView.setOnItemLongClickListener{
                _, _, position, _ -> removeStudent(position)
            true
        }
        //특정 아이디를 통해 값 가져오기
        listView.setOnItemClickListener { _, _, position, _ ->
            val studentName = studentList[position]

            //실제 보이게 하고 toast를 통해 어떤 텍스트를 띄울지 결정
            Toast.makeText(
                this,
                "Selected : $studentName (Position : ${position+1})",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Selected : $studentName at position $position")
        }
        Log.d(TAG, "Event listeners setup complete")
    }

    private fun addStudent(){
        val studentName = editTextStudent.text.toString().trim()

        if(studentName.isEmpty()){
            Toast.makeText(this,"please enter a student name",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add empty student name")
            return
        }

        if(studentList.contains(studentName)){
            Toast.makeText(this,"Student '$studentName' already exists",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add duplicate student : $studentName")
            return
        }

        studentList.add(studentName)
        adapter.notifyDataSetChanged()
        editTextStudent.text.clear()
        updateStudentCount()
        Toast.makeText(this,"added: $studentName ",Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Added : $studentName(Total ${studentList.size}) ")
    }
    private fun clearAllstudents(){
        if(studentList.isEmpty()){
            Toast.makeText(this,"List is already empty",Toast.LENGTH_SHORT).show()
            return
        }

        val count = studentList.size
        studentList.clear()
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Toast.makeText(this,"Cleared all $count students",Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Cleared all student (Total cleared : $count) ")
    }


    private fun updateStudentCount(){
        textViewCount.text="Total Students : ${studentList.size}"
    }

    private fun removeStudent(position:Int){
        if(position>= 0 && position < studentList.size ) {
            val removedStudent = studentList.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this,"Removed: $removedStudent",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Removed student : $removedStudent(Remaining : ${studentList.size})")
        }
    }

    private fun addInitialData(){
        val initialStudent = listOf("Kim","Lee","Park")
        studentList.addAll(initialStudent)
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Log.d(TAG,"Added initial data : $initialStudent")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume : Current  student count : ${studentList.size}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause : Saving  student count : ${studentList.size}")
    }
}