package edu.bmstu.stas.lab3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String login = "";
        String password = "";

        login = getIntent().getExtras().getString("login");
        password = getIntent().getExtras().getString("password");

        TextView infoTextView =
                (TextView)findViewById(R.id.activity_about_content);
        infoTextView.setText(login + " , вам передали " + password);


    }
}
