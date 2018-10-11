package com.example.hp.tourist.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.tourist.R;
import com.example.hp.tourist.TableFragment;


public class FragmentRutas extends Fragment implements View.OnClickListener{

    private View view;
    private Button btnHistoRut,btnMunVisit,btnSharedUb,btnWhoisNe,btnMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rutas, container, false);
        init();
        return view;
    }


    public void init(){
        btnHistoRut = view.findViewById(R.id.btnHistoRut);
        btnMunVisit = view.findViewById(R.id.btnMunVisit);
        btnSharedUb = view.findViewById(R.id.btnSharedUb);
        btnWhoisNe= view.findViewById(R.id.btnWhoisNe);
        btnMap = view.findViewById(R.id.btnMap);

        btnMap.setOnClickListener(this);
        btnHistoRut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista){
            case R.id.btnHistoRut:{
                TableFragment ta= new TableFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrFragment, ta,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

                break;
            }
            case R.id.btnMap:{
                /* MapFragment nextFrag= new MapFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrFragment, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();*/

                Intent firstpage= new Intent(getActivity(),MapsFragment.class);
                getActivity().startActivity(firstpage);
                break;
            }
        }
    }
}
