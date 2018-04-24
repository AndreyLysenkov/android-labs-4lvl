package edu.bmstu.stas.lab6;

import android.Manifest;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.requestPermission();

        this.fetchMusicData();
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                0);
    }

    private void fetchMusicData() {
        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.ARTIST); // sort order;

        this.viewMusic(cursor);
    }

    public void viewMusic(Cursor cursor) {
        Log.i("view", "binding adapter");
        ListView view = (ListView) findViewById(R.id.activity_main_view);
        RecordAdapter adapter = new RecordAdapter(this, cursor);
        view.setAdapter(adapter);
    }

    public void onRefresh(View view) {
        this.fetchMusicData();
    }

}
