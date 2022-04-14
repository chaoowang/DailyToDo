package au.edu.jcu.cp3406.dailytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    public static final int SETTINGS_REQUEST = 54624746 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button dark_btn = findViewById(R.id.dark_btn);
        dark_btn.setOnClickListener(v -> darkTheme(dark_btn));

        Button bright_btn = findViewById(R.id.bright_btn);
        bright_btn.setOnClickListener(v -> brightTheme(bright_btn));
    }

    public void darkTheme(View view){
        Intent data = new Intent();
        data.putExtra("theme", "dark");
        setResult(RESULT_OK, data);
        finish();
    }

    public void brightTheme(View view){
        Intent data = new Intent();
        data.putExtra("theme", "bright");
        setResult(RESULT_OK, data);
        finish();
    }
}