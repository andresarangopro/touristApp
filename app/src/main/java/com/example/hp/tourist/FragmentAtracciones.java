package com.example.hp.tourist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentAtracciones extends Fragment {

    private View view;
    private ArrayList<AtraccionTuristica> mData = new ArrayList<>();
    private RecyclerView mRecyclerDates;
    private RVTouristAtraction mDates;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_atracciones, container, false);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.caldas);

        mData.add(new AtraccionTuristica(null, "Alcaldía", drawable));
        mData.add(new AtraccionTuristica(null, "Alcaldía", drawable));
        mData.add(new AtraccionTuristica(null, "Alcaldía", drawable));
        initRecycler();
        return view;
    }

    private void initRecycler(){


        mRecyclerDates = view.findViewById(R.id.rvAtracciones) ;
        mRecyclerDates.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerDates.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerDates.addItemDecoration(dividerItemDecoration);

        mDates = new RVTouristAtraction(mData, view.getContext());
        mRecyclerDates.setAdapter( mDates);

    }

}
