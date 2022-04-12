package au.edu.jcu.cp3406.dailytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTasks(true, "Check the checkbox when finish the task");
        addTasks(false, "Delete the task with the x button on right.");

        Button add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(v -> addTask(add_btn));
        Button delete_btn = findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(view -> deleteTask(delete_btn));
    }

    private void addTasks(boolean checked, String description) {
        ViewGroup list = findViewById(R.id.list);
        getLayoutInflater().inflate(R.layout.list_item, list);

        View lastChild = list.getChildAt(list.getChildCount() - 1);
        TableRow task = lastChild.findViewById(R.id.task);

        CheckBox checkBox = task.findViewById(R.id.checkbox);
        EditText task_description = task.findViewById(R.id.description);

        checkBox.setChecked(checked);
        task_description.setText(description);
    }

    public void addTask(View view) {
        addTasks(false, "");
    }

    public void deleteTask(View view) {
        Button button = (Button) view;
        View task = (View) button.getParent();
        ViewGroup list = findViewById(R.id.list);
        list.removeView(task);
    }
}