package com.example.to_do_list;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private Context context;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskText.setText(task.getTaskText());

        // Edit Task
        holder.editButton.setOnClickListener(v -> {
            EditText input = new EditText(context);
            input.setText(task.getTaskText());

            new AlertDialog.Builder(context)
                    .setTitle("Edit Task")
                    .setView(input)
                    .setPositiveButton("Update", (dialog, which) -> {
                        String newText = input.getText().toString();
                        task.setTaskText(newText);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // Delete Task
        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Task")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        taskList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;
        ImageButton editButton, deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}