package au.edu.jcu.cp3406.dailytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences ToDoTasks;
    private SharedPreferences.Editor editor;
    public static final String TASKS = "tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToDoTasks = getSharedPreferences(TASKS, MODE_PRIVATE);
        editor = ToDoTasks.edit();

        int num = ToDoTasks.getAll().size() / 2;
        if (num == 0) {
            initExample();
        } else {
            load();
        }

        Button add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(v -> addTask(add_btn));
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

        Button delete_btn = task.findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(v -> deleteTask(delete_btn));
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

    private void save() {
        ToDoTasks = getSharedPreferences(TASKS, MODE_PRIVATE);
        editor = ToDoTasks.edit();
        ViewGroup list = findViewById(R.id.list);
        editor.clear();
        for (int i = 0; i < list.getChildCount(); i++) {
            TableRow task = (TableRow) list.getChildAt(i);

            EditText description = (EditText) task.getChildAt(1);
            Editable text = description.getText();
            String task_num = String.format(Locale.getDefault(), "task_%d", i);
            editor.putString(task_num, text.toString());

            CheckBox checkBox = (CheckBox) task.getChildAt(0);
            boolean checked = checkBox.isChecked();
            String check_num = String.format(Locale.getDefault(), "check_%d", i);
            editor.putBoolean(check_num, checked);
        }
        editor.commit();
    }

    private void load() {
        int num = ToDoTasks.getAll().size() / 2;
        for (int i = 0; i < num; i++) {
            String task_num = String.format(Locale.getDefault(), "task_%d", i);
            String check_num = String.format(Locale.getDefault(), "check_%d", i);
            String description = ToDoTasks.getString(task_num, "");
            boolean checked = ToDoTasks.getBoolean(check_num, false);
            addTasks(checked, description);
        }
    }

    private void initExample() {
        addTasks(true, "Check the checkbox when finish the task");
        addTasks(false, "Delete the task with the x button on right.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }
}