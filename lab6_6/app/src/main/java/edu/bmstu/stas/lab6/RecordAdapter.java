package edu.bmstu.stas.lab6;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends CursorAdapter {

    public RecordAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.music_record, parent, false);
    }

    // https://stackoverflow.com/questions/4142313/java-convert-milliseconds-to-time-format
    private String durationToString(int duration, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(duration));
    }

    private String replaceByTemplate(String source, String[] templates, String[] values) {
        for (int i = 0; i < Math.min(templates.length, values.length); i++)
        {
            source = source.replace(templates[i], values[i]);
        }
        return source;
    }

    private String getGenresByMusicId(Context context, int id) {
        List<String> result = new ArrayList<>();
        Uri uri = MediaStore.Audio.Genres.getContentUriForAudioId("external", id);
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);
        //startManagingCursor(cursor);
        cursor.moveToFirst();
        if (cursor.getCount() < 1)
            return "none";
        do {
            result.add(cursor.getString(index));
        } while (cursor.moveToNext());
        return TextUtils.join(", ", result);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String line_1_content = context.getResources().getString(R.string.record_line_1_template);
        String line_2_content = context.getResources().getString(R.string.record_line_2_template);

        String artist_template = context.getResources().getString(R.string.record_artist_template);
        String name_template = context.getResources().getString(R.string.record_name_template);
        String album_template = context.getResources().getString(R.string.record_album_template);
        String duration_template = context.getResources().getString(R.string.record_duration_template);
        String genre_template = context.getResources().getString(R.string.record_genre_template);

        String artist = cursor.getString(cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.ARTIST
        ));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.TITLE
        ));
        String duration = this.durationToString(
                cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DURATION
                )),
                context.getResources().getString(R.string.record_duration_format));
        String album = cursor.getString(cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.ALBUM
        ));
        String genre = this.getGenresByMusicId(
                context,
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));

        String[] templates = new String[]{
                artist_template,
                name_template,
                duration_template,
                album_template,
                genre_template
        };

        String[] values = new String[]{
                artist,
                name,
                duration,
                album,
                genre
        };

        line_1_content = this.replaceByTemplate(line_1_content, templates, values);
        line_2_content = this.replaceByTemplate(line_2_content, templates, values);

        TextView line_1 = view.findViewById(R.id.record_line_1);
        line_1.setText(line_1_content);

        TextView line_2 = view.findViewById(R.id.record_line_2);
        line_2.setText(line_2_content);
    }

}
