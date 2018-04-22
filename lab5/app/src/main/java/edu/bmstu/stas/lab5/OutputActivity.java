package edu.bmstu.stas.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    String content;
    int color;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        Bundle extras = getIntent().getExtras();

        this.content = extras.getString("content");
        this.size = extras.getInt("size");
        this.color = extras.getInt("color");

        this.setView();
    }

    private void setView() {
        TextView view = findViewById(R.id.activity_output_content);

        view.setText(this.content);
        view.setTextSize(this.size);
        view.setTextColor(this.color);
    }
}
