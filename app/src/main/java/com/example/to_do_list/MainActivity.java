package com.example.to_do_list;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     List<Task> taskList;
     TaskAdapter taskAdapter;
     EditText editTextTask;
     Button btnAddTask;
     RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editTextTask = findViewById(R.id.editTextTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        btnAddTask.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                taskList.add(new Task(taskText));
                taskAdapter.notifyDataSetChanged();
                editTextTask.setText("");
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });



    }
}