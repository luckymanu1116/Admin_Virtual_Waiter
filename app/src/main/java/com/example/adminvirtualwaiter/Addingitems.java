package com.example.adminvirtualwaiter;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Addingitems extends Fragment {

ImageButton veg,nonveg,starters,desserts;
Button button;
    public Addingitems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_addingitems,container,false);
        veg=(ImageButton)view.findViewById(R.id.imageButton3);
        button=(Button)view.findViewById(R.id.button6);
        button.setEnabled(false);
        nonveg=(ImageButton)view.findViewById(R.id.imageButton2);
        starters=(ImageButton)view.findViewById(R.id.imageButton);
        desserts=(ImageButton)view.findViewById(R.id.imageButton4);
        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NonVegItems.class);
                getActivity().startActivity(intent);
            }
        });
        starters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Starters.class);
                getActivity().startActivity(intent);
            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VegItems.class);
                getActivity().startActivity(intent);
            }
        });
        desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Desserts.class);
                getActivity().startActivity(intent);
            }
        });

                return view;
    }

}
