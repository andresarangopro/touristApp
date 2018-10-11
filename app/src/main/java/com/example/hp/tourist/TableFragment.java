package com.example.hp.tourist;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hp.tourist.Clases.Transacciones;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TableFragment extends Fragment {


    public  List<Ruta> listRutas = new ArrayList();
    private Transacciones tr = new Transacciones();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_table, container, false);
        tr.inicializatedFireBase(view.getContext());
        getNodes(new Ruta());
        return view;
    }


    private void addTable(){

        TableLayout tl = view.findViewById(R.id.tvRow);

        for (Ruta objects: listRutas) {
            TableRow tr = new TableRow(view.getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.setBackgroundColor(Color.WHITE);
            tr.setWeightSum(3);
            /**
             * Crear un tv para cada campo
             */
            TextView tv = new TextView(view.getContext());
            tv.setText(objects.getFecha());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            TextView tv1 = new TextView(view.getContext());
            tv1.setText(objects.getPuntoInicio().getLatitude() + "," + objects.getPuntoInicio().getLongitude());
            tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            TextView tv2 = new TextView(view.getContext());
            tv2.setText(objects.getPuntoFinal().getLatitude() + "," + objects.getPuntoFinal().getLongitude());
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));


            /**
             * Agregar todos los tvs
             */
            tr.addView(tv);
            tr.addView(tv1);
            tr.addView(tv2);
            /**
             * Agregar el tr
             */
            tl.addView(tr);
        }

    }

    public void getNodes(final Ruta ruta){
        listRutas = new ArrayList<>();
        tr.getDatabaseReference().child(ruta.getFirebaseNodeName())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ruta object = snapshot.getValue(ruta.getClass());
                        listRutas.add(object);
                    }
                    addTable();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
    }

}
