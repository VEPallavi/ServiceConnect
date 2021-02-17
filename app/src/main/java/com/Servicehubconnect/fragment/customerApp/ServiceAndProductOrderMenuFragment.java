package com.Servicehubconnect.fragment.customerApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Servicehubconnect.R;
import com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct.ServiceAndProductOrderMenuAdapter;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.CategoryInfo;

import java.util.ArrayList;

public class ServiceAndProductOrderMenuFragment extends Fragment {
    RecyclerView rv_service_and_order_menu;
    LinearLayoutManager linearLayoutManager;
    TextView c;
    int val;
    private Activity activity;
    private ArrayList<CategoryInfo> categoryInfoList;
    ServiceAndProductOrderMenuAdapter serviceAndProductOrderMenuAdapter;
    String categoryType;




    @SuppressLint("ValidFragment")
    public ServiceAndProductOrderMenuFragment(Activity activity, ArrayList<CategoryInfo> categoryInfoList, String categoryType) {
        this.activity = activity;
        this.categoryInfoList = categoryInfoList;
        this.categoryType = categoryType;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_service_and_order_menu,container, false);
        rv_service_and_order_menu = view.findViewById(R.id.rv_service_and_order_menu);

        rv_service_and_order_menu.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_service_and_order_menu.setLayoutManager(linearLayoutManager);

        serviceAndProductOrderMenuAdapter = new ServiceAndProductOrderMenuAdapter(getActivity(), categoryInfoList, categoryType);
        rv_service_and_order_menu.setAdapter(serviceAndProductOrderMenuAdapter);


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }







/*    public static ServiceAndProductOrderMenuFragment newInstance(String val) {
//        ServiceAndProductOrderMenuFragment fragment = new ServiceAndProductOrderMenuFragment();
//        Bundle args = new Bundle();
//        args.putString("someValue", val);
//        fragment.setArguments(args);
//        return fragment;


//        ServiceAndOrderMenuFragment fragment = new ServiceAndOrderMenuFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", val);
//        fragment.setArguments(args);
//        return fragment;
    }*/



}
