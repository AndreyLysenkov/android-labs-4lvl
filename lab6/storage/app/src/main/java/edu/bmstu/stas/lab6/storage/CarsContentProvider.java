package edu.bmstu.stas.lab6.storage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

// thnx https://developer.android.com/guide/topics/providers/content-provider-creating.html
// http://www.nerdgrl.org/ru/programming/contentprovider-2/
public class CarsContentProvider extends ContentProvider {

    private CarsDatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    private MatcherHelper matcherHelper;

    // https://developer.android.com/reference/android/content/UriMatcher.html

    @Override
    public boolean onCreate() {
        this.databaseHelper = new CarsDatabaseHelper(getContext());
        this.matcherHelper = new MatcherHelper();

        return true;
    }

    private void openDatabase(boolean isWriteable) {
        this.database = isWriteable ?
            this.databaseHelper.getWritableDatabase()
            : this.databaseHelper.getReadableDatabase();
    }

    public String getType(Uri uri) {
        // TODO;

        final String separator = "/";

        switch (this.matcherHelper.matcher.match(uri))
        {
            case MatcherHelper.DELETE_TEM:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.DELETE_PATH;
            case MatcherHelper.DELETE_SELECTION:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.DELETE__SELECTION_PATH;
            case MatcherHelper.UPDATE_ITEM:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.UPDATE_ITEM_PATH;
            case MatcherHelper.UPDATE_SELECTION:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.UPDATE_SELECTION_PATH;
            case MatcherHelper.FETCH_ALL:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.FETCH_ALL_PATH;
            case MatcherHelper.FETCH_ITEM:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.FETCH_ITEM_PATH;
            case MatcherHelper.FETCH_QUERY:
                return MatcherHelper.AUTHORITY + separator + MatcherHelper.FETCH_QUERY_PATH;
        }
        return null;
    }

    // https://www.tutorialspoint.com/android/android_content_providers.htm
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        this.openDatabase(true);
        long id = this.database.insert(CarsDatabaseHelper.TABLE_NAME, null, values);

        if (id < 0)
            throw new SQLException(getContext().getResources().getString(R.string.database_insert_fail)
                    .replace("%s%", uri.toString()));

        Uri result = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(result, null);
        // TODO;
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        this.openDatabase(true);

        int count = 0;

        // TODO;

        switch (this.matcherHelper.matcher.match(uri)){
            case MatcherHelper.DELETE_SELECTION:
                count = this.database.delete(CarsDatabaseHelper.TABLE_NAME, selection, selectionArgs);
                break;

            case MatcherHelper.DELETE_TEM:
                String id = uri.getPathSegments().get(1);
                count = this.database.delete(CarsDatabaseHelper.TABLE_NAME, "_ID = " + id, selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        this.openDatabase(true);

        int count = 0;

        // TODO;

        switch (this.matcherHelper.matcher.match(uri)) {
            case MatcherHelper.UPDATE_SELECTION:
                count = this.database.update(CarsDatabaseHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case MatcherHelper.UPDATE_ITEM:
                this.database.update(CarsDatabaseHelper.TABLE_NAME,
                        values,
                        "_ID = " + uri.getPathSegments().get(1),
                        selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        this.openDatabase(false);
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(CarsDatabaseHelper.TABLE_NAME);
        Cursor result;


        switch (matcherHelper.matcher.match(uri)) {
            case MatcherHelper.FETCH_ITEM:
                builder.appendWhere("_ID = " + uri.getPathSegments().get(1));
                break;
            case MatcherHelper.FETCH_QUERY:
                break;
        }

        // TODO;
        result = builder.query(this.database, projection, selection, selectionArgs, null, null, sortOrder);
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

}
