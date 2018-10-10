package com.example.hp.tourist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableActivity extends AppCompatActivity {

    List<Object> objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        objects.add("HOLA");
        objects.add("Cómo");
        objects.add("Te");
        objects.add("Va");

        addTable();
    }

    private void addTable(){

        TableLayout tl = (TableLayout) findViewById(R.id.tvRow);

        for (Object objects: objects) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.setBackgroundColor(Color.WHITE);
            tr.setWeightSum(4);
            /**
             * Crear un tv para cada campo
             */
            TextView tv = new TextView(this);
            tv.setText(objects.toString());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            /**
             * Agregar todos los tvs
             */
            tr.addView(tv);

            /**
             * Agregar el tr
             */
            tl.addView(tr);
        }

    }
}
