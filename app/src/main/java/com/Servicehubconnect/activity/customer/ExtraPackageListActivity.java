package com.Servicehubconnect.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.Servicehubconnect.R;
import com.Servicehubconnect.helper.AppConstants;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ExtraInfoForProductAndService;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ServiceAndProductListDataModal;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.Servicehubconnect.viewModel.customer.ExtraPackageListViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ExtraPackageListActivity extends AppCompatActivity {
    ArrayList<ExtraInfoForProductAndService> extraServiceAndProductList = new ArrayList();
    ExtraPackageListViewModel viewModel;
    TextView txt_item_name, txt_item_price, txt_item_time, txt_selected_extras, txt_total_amount;
    LinearLayout ln_layout_extras_container;
    RelativeLayout rlt_layout_add_extras;
    private StoreItemDetailsListCategoryInfo storeItemDetailsModel;
    String extraId;
    String category_type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_extra_package_list);

        viewModel = ViewModelProviders.of(this).get(ExtraPackageListViewModel.class);


        initViews();
        getIntentData();
        setData();

    }

    private void getIntentData() {
        Intent intent = getIntent();

        if(intent != null){
            category_type = getIntent().getStringExtra(AppConstants.CATEGORY_TYPE);

            String jsonStoreItemDetailsModel = intent.getStringExtra(AppConstants.STORE_ITEM_DETAILS);
            storeItemDetailsModel = new Gson().fromJson(jsonStoreItemDetailsModel, StoreItemDetailsListCategoryInfo.class);
        }

    }

    private void initViews() {
        txt_item_name = findViewById(R.id.txt_item_name);
        txt_item_price = findViewById(R.id.txt_item_price);
        txt_item_time = findViewById(R.id.txt_item_time);
        txt_selected_extras = findViewById(R.id.txt_selected_extras);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        ln_layout_extras_container = findViewById(R.id.ln_layout_extras_container);
        rlt_layout_add_extras = findViewById(R.id.rlt_layout_add_extras);
    }




    private void setData() {
        if(storeItemDetailsModel != null){
            extraId = storeItemDetailsModel.getExtraId();
            category_type = category_type;

            txt_item_name.setText(storeItemDetailsModel.getName());
        }


        viewModel.getExtraPackageList(this, category_type, extraId).observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject it) {

                if(it!= null){

                    if(it.has("status") && it.get("status").getAsString().equals("200")){

                        if(it.has("extraInfo") && it.get("extraInfo") instanceof JsonArray) {

                            Type type = new TypeToken<ArrayList<ExtraInfoForProductAndService>>() {}.getType();
                            ArrayList<ExtraInfoForProductAndService> dataList = new Gson().fromJson(it.get("extraInfo"), type);

                            if(dataList.size() >0){
                                extraServiceAndProductList.addAll(dataList);



                            }
                        }
                    }
                }
            }
        });

    }


}
