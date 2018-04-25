package edu.bmstu.stas.lab6;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class PeopleContentProvider extends ContentProvider {

    public static final String DATABASE_NAME = "people";
    public static final int DATABASE_VERSION = 1;
    public static final String PEOPLE_TABLE_NAME = "people";

    public static final String ID_FIELD = "id";
    public static final String FIRST_NAME_FIELD = "first_name";
    public static final String SECOND_NAME_FIELD = "first_name";
    public static final String PHONE_FIELD = "phone";

    public static final String CREATE_TABLE = "CREATE TABLE " + PEOPLE_TABLE_NAME + " (" +
            ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIRST_NAME_FIELD + " TEXT, " +
            SECOND_NAME_FIELD +" TEXT, " +
            PHONE_FIELD + " TEXT );";

    public static final String AUTHORITY = "edu.bmstu.stas.provider.database";

    public static final String URL = "content://" + AUTHORITY + "/people";
    public static final Uri URI = Uri.parse(URL);

    public static final int URI_PEOPLE = 1;
    public static final int URI_ITEM = 2;

    private static HashMap<String, String> PROJECTION_MAP;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "people/*", URI_PEOPLE);
        uriMatcher.addURI(AUTHORITY, "item/#", URI_ITEM);
    }

    private SQLiteDatabase database;

    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper helper = new DatabaseHelper(context);

        database = helper.getWritableDatabase();
        return database != null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_PEOPLE:
                return "vndr.android.cursor.dir/vnd.bmstu.people";
            case URI_ITEM:
                return "vndr.android.cursor.item/vnd.bmstu.people";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = this.database.insert(PEOPLE_TABLE_NAME, null, values);

        // https://www.sqlite.org/autoinc.html - 1 is the first id;
        if (id <= 0)
            return null;

        Uri newUri = ContentUris.withAppendedId(URI, id);
        getContext().getContentResolver().notifyChange(newUri, null);
        return  newUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case URI_PEOPLE:
                count = this.database.update(PEOPLE_TABLE_NAME, values, selection, selectionArgs);
                break;
            case URI_ITEM:
                String newSelection = ID_FIELD + " = " + uri.getPathSegments().get(1);
                if (!TextUtils.isEmpty(selection))
                    newSelection += " AND (" + selection + ")";
                count = this.database.update(PEOPLE_TABLE_NAME, values, newSelection, selectionArgs);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case URI_PEOPLE:
                count = this.database.delete(PEOPLE_TABLE_NAME, selection, selectionArgs);
                break;
            case URI_ITEM:
                String newSelection = ID_FIELD + " = " + uri.getPathSegments().get(1);
                if (!TextUtils.isEmpty(selection))
                    newSelection += " AND (" + selection + ")";
                count = this.database.delete(PEOPLE_TABLE_NAME, selection, selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(PEOPLE_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case URI_PEOPLE:
                builder.setProjectionMap(PROJECTION_MAP);
                break;
            case URI_ITEM:
                builder.appendWhere(ID_FIELD + " = " + uri.getPathSegments().get(1));
            break;
        }

        if (TextUtils.isEmpty(sortOrder))
            sortOrder = ID_FIELD;

        Cursor cursor = builder.query(
                this.database,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

}
