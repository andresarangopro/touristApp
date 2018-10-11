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

public class TableActivity extends Fragment {

    List<Object> objects = new ArrayList<>();
    public  List<Ruta> listRutas = new ArrayList();
    private Transacciones tr = new Transacciones();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_table, container, false);

        objects.add("HOLA");
        objects.add("Cómo");
        objects.add("Te");
        objects.add("Va");
        tr.inicializatedFireBase(view.getContext());
        addTable();
        getNodes(new Ruta());
        return view;
    }


    private void addTable(){

        TableLayout tl = (TableLayout) view.findViewById(R.id.tvRow);

        for (Object objects: objects) {
            TableRow tr = new TableRow(view.getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.setBackgroundColor(Color.WHITE);
            tr.setWeightSum(4);
            /**
             * Crear un tv para cada campo
             */
            TextView tv = new TextView(view.getContext());
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

    public void getNodes(final Ruta ruta){
        listRutas = new ArrayList<>();
        final FirebaseUser usa = tr.firebaseAuth.getCurrentUser();
        tr.getDatabaseReference().child(ruta.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Ruta object = snapshot.getValue(ruta.getClass());
                            listRutas.add(object);
                            Toast.makeText(view.getContext(), "s-- "+object.getFecha(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

}
