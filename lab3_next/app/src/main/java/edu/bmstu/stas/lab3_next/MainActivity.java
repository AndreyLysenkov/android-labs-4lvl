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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 0:
                int color = data.getExtras().getInt("color");
                TextView text = findViewById(R.id.activity_main_text);
                text.setTextColor(color);
                break;
        }
    }
}


/*
@Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
}

 public void onClick(View view) {
     Uri address = Uri.parse("https://developer.android.com/index.html");
     Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
     startActivity(openlinkIntent);
 }
 */