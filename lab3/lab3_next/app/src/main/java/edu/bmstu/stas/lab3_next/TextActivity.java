package edu.bmstu.stas.lab3_next;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }

    public void onClick(View view) {
        // thnx https://developer.android.com/guide/topics/ui/controls/radiobutton.html

        EditText text = findViewById(R.id.activity_text_field);

        Intent intent = getIntent();
        intent.putExtra("text", text.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}
