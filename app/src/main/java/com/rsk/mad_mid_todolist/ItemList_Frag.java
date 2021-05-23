package com.rsk.mad_mid_todolist;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ItemList_Frag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter myAdapter;



    public ItemList_Frag() {
        // Required empty public constructor
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //myActivity=(ItemSelected)context;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        myAdapter=new ItemAdapter(this.getActivity(),AddList_Activity.item);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_item_list_, container, false);

        recyclerView=view.findViewById(R.id.rvItemList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return view;
    }
}