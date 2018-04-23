package edu.bmstu.stas.lab6.storage;

import android.content.UriMatcher;

public class MatcherHelper {

    public static final String AUTHORITY = "edu.bmstu.stas.lab6.storage";

    public static final int DELETE_TEM = 1;

    public static final String DELETE_PATH = "delete/item/#";

    public static final int DELETE_SELECTION = 2;

    public static final String DELETE__SELECTION_PATH = "delete/selection/*";

    public static final int UPDATE_ITEM = 3;

    public static final String UPDATE_ITEM_PATH = "update/item/#";

    public static final int UPDATE_SELECTION = 4;

    public static final String UPDATE_SELECTION_PATH = "update/selection/#";

    public static final int FETCH_ALL = 5; // TODO;

    public static final String FETCH_ALL_PATH = "fetch/all"; // TODO; for removal?

    public static final int FETCH_ITEM = 6;

    public static final String FETCH_ITEM_PATH = "fetch/#";

    public static final int FETCH_QUERY = 7;

    public static final String FETCH_QUERY_PATH = "fetch/query/*";

    // TODO;

    public UriMatcher matcher;

    public void onCreate()
    {
        this.matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, DELETE_PATH, DELETE_TEM);
        matcher.addURI(AUTHORITY, DELETE__SELECTION_PATH, DELETE_SELECTION);
        matcher.addURI(AUTHORITY, UPDATE_ITEM_PATH, UPDATE_ITEM);
        matcher.addURI(AUTHORITY, UPDATE_SELECTION_PATH, UPDATE_SELECTION);
        matcher.addURI(AUTHORITY, FETCH_ALL_PATH, FETCH_ALL);
        matcher.addURI(AUTHORITY, FETCH_ITEM_PATH, FETCH_ITEM);
        matcher.addURI(AUTHORITY, FETCH_QUERY_PATH, FETCH_QUERY);
        // TODO;
    }

}
