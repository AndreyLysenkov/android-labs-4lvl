package edu.bmstu.stas.lab4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

// thnx https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter

public class QueryAdapter extends CursorAdapter {
    public QueryAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.qurey_output, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView dataId = view.findViewById(R.id.query_id_value);
        int idValue = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        dataId.setText(Integer.toString(idValue));

        TextView dataType = view.findViewById(R.id.query_type_value);
        String typeValue = cursor.getString(cursor.getColumnIndexOrThrow("type"));
        dataType.setText(typeValue);

        TextView dataManufacture = view.findViewById(R.id.query_manufacture_value);
        String manufactureValue = cursor.getString(cursor.getColumnIndexOrThrow("manufacture"));
        dataManufacture.setText(manufactureValue);

        TextView dataModel = view.findViewById(R.id.query_model_value);
        String modelValue = cursor.getString(cursor.getColumnIndexOrThrow("model"));
        dataModel.setText(modelValue);

        TextView dataBaggage = view.findViewById(R.id.query_baggage_value);
        int baggageValue = cursor.getInt(cursor.getColumnIndexOrThrow("baggage"));
        dataBaggage.setText(Integer.toString(baggageValue));

        TextView dataAbs = view.findViewById(R.id.query_abs_value);
        int absValue = cursor.getInt(cursor.getColumnIndexOrThrow("abs"));
        dataAbs.setText(Integer.toString(absValue));

        TextView dataSafety = view.findViewById(R.id.query_safety_value);
        int safetyValue = cursor.getInt(cursor.getColumnIndexOrThrow("safety"));
        dataSafety.setText(Integer.toString(safetyValue));

        TextView dataConsumption = view.findViewById(R.id.query_consumption_value);
        float consumptionValue = cursor.getFloat(cursor.getColumnIndexOrThrow("consumption"));
        dataConsumption.setText(Float.toString(consumptionValue));

    }
}