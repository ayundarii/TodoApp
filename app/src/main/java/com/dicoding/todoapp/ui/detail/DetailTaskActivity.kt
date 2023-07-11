package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailTaskViewModel
    private lateinit var edtTitle: TextInputEditText
    private lateinit var edtDesc: TextInputEditText
    private lateinit var edtDueDate: TextInputEditText
    private lateinit var btnDeleteTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        edtTitle = findViewById(R.id.detail_ed_title)
        edtDesc = findViewById(R.id.detail_ed_description)
        edtDueDate = findViewById(R.id.detail_ed_due_date)
        btnDeleteTask = findViewById(R.id.btn_delete_task)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[DetailTaskViewModel::class.java]

        val taskId = intent.getIntExtra(TASK_ID, 0)
        viewModel.setTaskId(taskId)

        viewModel.task.observe(this) { task ->
            task?.let {
                edtTitle.setText(it.title)
                edtDesc.setText(it.description)
                edtDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }

        btnDeleteTask.setOnClickListener {
            viewModel.deleteTask()
            onBackPressed()
            Toast.makeText(applicationContext, "Your task has been deleted.", Toast.LENGTH_SHORT).show()
        }
    }
}