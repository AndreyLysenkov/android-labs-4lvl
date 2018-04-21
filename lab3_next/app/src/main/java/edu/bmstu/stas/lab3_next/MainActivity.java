package edu.bmstu.stas.lab3_next;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();

        int color = intent.getExtras().getInt("color");
        //Log.d("color", color);*/
    }

    public void onColorPick(View view) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onTextEdit(View view) {
        Intent intent = new Intent(MainActivity.this, TextActivity.class);
        startActivityForResult(intent, 1);

    }

    public void onSizeEdit(View view) {
        Intent intent = new Intent(MainActivity.this, SizeActivity.class);
        startActivityForResult(intent, 2);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView text = findViewById(R.id.activity_main_text);

        switch (requestCode) {
            case 0:
                int color = data.getExtras().getInt("color");
                text.setTextColor(color);
                break;
            case 1:
                String str = data.getExtras().getString("text");
                text.setText(str);
                break;
            case 2:
                int size = data.getExtras().getInt("size");
                text.setTextSize(size);
                break;
        }
    }
}