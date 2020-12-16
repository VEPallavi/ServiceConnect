package com.Servicehubconnect.fragment.customerApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.Servicehubconnect.R;

public class ServiceAndOrderMenuFragment extends Fragment {
    RecyclerView rv_service_and_order_menu;
    TextView c;
    int val;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_service_and_order_menu,container, false);
        rv_service_and_order_menu = view.findViewById(R.id.rv_service_and_order_menu);
        val = getArguments().getInt("someInt", 0);
        c = view.findViewById(R.id.c);
        c.setText("Fragment - " + val);
        return view;
    }

    public static ServiceAndOrderMenuFragment newInstance(int val) {
        ServiceAndOrderMenuFragment fragment = new ServiceAndOrderMenuFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
