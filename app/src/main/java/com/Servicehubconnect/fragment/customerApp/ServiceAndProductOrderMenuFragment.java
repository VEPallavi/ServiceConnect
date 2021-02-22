package com.Servicehubconnect.fragment.customerApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Servicehubconnect.R;
import com.Servicehubconnect.activity.customer.OrderProductsAndServicesKotlinActivity;
import com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct.ServiceAndProductOrderMenuAdapter;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ServiceAndProductOrderMenuFragment extends Fragment {
    RecyclerView rv_service_and_order_menu;
    LinearLayoutManager linearLayoutManager;
    TextView c;
    int val;
    private Activity activity;
    private ArrayList<StoreItemDetailsListCategoryInfo> categoryInfoList;
    ServiceAndProductOrderMenuAdapter serviceAndProductOrderMenuAdapter;
    String categoryType;




    @SuppressLint("ValidFragment")
    public ServiceAndProductOrderMenuFragment(Activity activity, ArrayList<StoreItemDetailsListCategoryInfo> categoryInfoList, String categoryType) {
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

        serviceAndProductOrderMenuAdapter = new ServiceAndProductOrderMenuAdapter(getActivity(), categoryInfoList
                , OrderProductsAndServicesKotlinActivity.finalListHashMap, categoryType);


        serviceAndProductOrderMenuAdapter.setOnAddButtonClickListener(new AddOrderButtonClickListener());
        rv_service_and_order_menu.setAdapter(serviceAndProductOrderMenuAdapter);


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    private class AddOrderButtonClickListener implements ServiceAndProductOrderMenuAdapter.AddButtonClickListener {

        @Override
        public void onAddButtonClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType) {


            if (!storeItemDetailsListModel.is_size() && !storeItemDetailsListModel.isExtraPackage()) {

                addListToFinalHashMap(storeItemDetailsListModel);

            } else if (storeItemDetailsListModel.is_size() && !storeItemDetailsListModel.isExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                showSizePopup(vStoreItemDetailsListModel , false);

            } else if (storeItemDetailsListModel.is_size() && storeItemDetailsListModel.isExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                showSizePopup(vStoreItemDetailsListModel , false);

            } else if (!storeItemDetailsListModel.is_size() && storeItemDetailsListModel.isExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);

                Intent i = new Intent(activity, ExtraPackageListActivity.class);

                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());

                i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getStoreCategoriesItemPrice());

                startActivityForResult(i, 1);

            }

        }
    }




    private void showSizePopup(StoreItemDetailsListCategoryInfo storeItemDetailsListModel , Boolean edit) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_Dialog);

        LayoutInflater inflater = getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.pop_up_window_select_size, null);

       // initPopUpUI(dialogLayout, storeItemDetailsListModel , edit);


        for (int i = 0; i < storeItemDetailsListModel.getSize_price_duration().size(); i++) {
            RadioButton rbn = new RadioButton(getActivity());

            rbn.setText(getSpannableString(storeItemDetailsListModel.getSize_price().get(i).getSizeDescription()
                    , "   " + OrderFoodActivity.currencySymbol + String.format("%.2f"
                            , Double.parseDouble(storeItemDetailsListModel.getSizeDetailsList().get(i).getSizePrice()))));

            rbn.setId(i);

            if(storeItemDetailsListModel.getSizeDetailsList().get(i).isSelected())
            {
                rbn.setChecked(true);
            }

            rgSize.addView(rbn);

        }

//        -  call  - 31 min
//                -  diagram go through - 20 min
//                -  worked on the pop_up_window_select_size


                foodSizeSelectionDialog = builder.create();

        foodSizeSelectionDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        foodSizeSelectionDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        foodSizeSelectionDialog.setView(dialogLayout, 0, 0, 0, 0);

        foodSizeSelectionDialog.setCanceledOnTouchOutside(false);

        foodSizeSelectionDialog.setCancelable(true);

        WindowManager.LayoutParams params = foodSizeSelectionDialog.getWindow()
                .getAttributes();

        params.gravity = Gravity.BOTTOM;

        builder.setView(dialogLayout);

        foodSizeSelectionDialog.show();

    }


    private SpannableString getSpannableString(String name, String amount, String timeDuration) {

        String foodNameAmt = name + " " + amount;

        SpannableString styledString
                = new SpannableString(foodNameAmt);

        styledString.setSpan(new RelativeSizeSpan(1f), 0, name.length()+1, 0);

        styledString.setSpan(new RelativeSizeSpan(0.8f), name.length(), foodNameAmt.length(),0);


        styledString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, name.length()+1, 0);

        styledString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.color_restaurant_address)), name.length(), foodNameAmt.length(),0);


        return styledString;

    }



    private StoreItemDetailsListCategoryInfo getClonedvStoreItemDetailsListModelObject(StoreItemDetailsListCategoryInfo storeItemDetailsListModel) {

        StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = new StoreItemDetailsListCategoryInfo();

        String strJsonStoreListModel = new Gson().toJson(storeItemDetailsListModel);

        vStoreItemDetailsListModel = new Gson().fromJson(strJsonStoreListModel, StoreItemDetailsListModel.class);

        return vStoreItemDetailsListModel;
    }





    public void addListToFinalHashMap(StoreItemDetailsListCategoryInfo storeItemDetailsListModel) {



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
