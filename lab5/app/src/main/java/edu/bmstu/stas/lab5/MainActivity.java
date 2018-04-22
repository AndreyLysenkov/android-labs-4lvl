package edu.bmstu.stas.lab5;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// thnx http://developer.alexanderklimov.ru/android/views/expandablelistview.php
// https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
public class MainActivity extends AppCompatActivity {

    int[] sizes = new int[]{20, 40, 70};

    int[] colors = new int[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;

    ExpandableListView chooser;

    int chosen_size;
    int chosen_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chosen_size = 20;
        chosen_color = Color.BLACK;

        Map<String, String> atributes;
        this.groupData = new ArrayList<Map<String, String>>();
        String[] colorNames = getResources().getStringArray(R.array.activity_main_chooser_color);
        for (String colorName : colorNames) {
            // заполняем список атрибутов для каждой группы
            atributes = new HashMap<String, String>();
            atributes.put("colorLabel", colorName);
            groupData.add(atributes);
        }

        // список атрибутов групп для чтения
        String[] groupFrom = new String[] {"colorLabel"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int[] groupTo = new int[] {R.id.list_group};

        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        for (int color : colors) {
            // создаем коллекцию элементов для N группы
            childDataItem = new ArrayList<Map<String, String>>();
            // заполняем список атрибутов для каждого элемента
            for (int size : sizes) {
                atributes = new HashMap<String, String>();
                atributes.put("fontLabel", Integer.toString(size) + "dp"); // название телефона
                childDataItem.add(atributes);
            }
            // добавляем в коллекцию коллекций
            childData.add(childDataItem);
        }

        String[] childFrom = new String[] {"fontLabel"};
        int[] childTo = new int[] {R.id.list_item};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                R.layout.list_group,
                groupFrom,
                groupTo,
                childData,
                R.layout.list_item,
                childFrom,
                childTo
        );

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.activity_main_chooser);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
            {
                @Override
                public boolean onChildClick(
                        ExpandableListView parent, View v,
                        int groupPosition, int childPosition,
                        long id) {
                    MainActivity.this.onItemChoosed(groupPosition, childPosition);
                    return true;
                }
            });
    }


    public void onItemChoosed(int group, int item) {
        this.chosen_color = colors[group];
        this.chosen_size = sizes[item];
    }

    public void onApply(View view) {
        // TODO;



        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        intent.putExtra("content", ((EditText) findViewById(R.id.activity_main_content)).getText().toString());
        intent.putExtra("color", chosen_color);
        intent.putExtra("size", chosen_size);

        startActivity(intent);
    }
}
