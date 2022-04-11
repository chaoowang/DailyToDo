package au.edu.jcu.cp3406.dailytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTasks(true, "Check the checkbox when finish the task");
        addTasks(false,"Delete the task with the x button on right.");
    }

    private void addTasks(boolean checked, String description){
        ViewGroup list = findViewById(R.id.list);
        getLayoutInflater().inflate(R.layout.list_item, list);

        View lastChild = list.getChildAt(list.getChildCount()-1);
        CheckedTextView task = lastChild.findViewById(R.id.task).findViewById(R.id.checkedtext);

        task.setChecked(checked);
        task.setText(description);
    }
}