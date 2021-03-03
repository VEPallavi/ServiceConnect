package com.Servicehubconnect.activity.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.Servicehubconnect.R;
import com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct.ServiceAndProductOrderPagerAdapter;
import com.Servicehubconnect.fragment.customerApp.ServiceAndProductOrderMenuFragment;
import com.Servicehubconnect.helper.Utils;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ServiceAndProductListDataModal;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.Servicehubconnect.viewModel.customer.ProfessionalDetailsWithProductsAndServicesViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class OrderProductsAndServicesActivity extends AppCompatActivity implements View.OnClickListener {
    ProfessionalDetailsWithProductsAndServicesViewModel viewModel;
    Activity activity;
    TabLayout tabs;
    ViewPager viewPager;
    ServiceAndProductOrderPagerAdapter pagerAdapter;
    String professionalId, bussinessId, categoryName, categoryType;
    JsonObject allJsonData;
    ServiceAndProductListDataModal serviceAndProductListModal;
    ArrayList<ServiceAndProductListDataModal> serviceAndProductList = new ArrayList();
    ArrayList<StoreItemDetailsListCategoryInfo> categoryList = new ArrayList();
    StoreItemDetailsListCategoryInfo categoryInfoModal;
    public static String currencySymbol = "";
    public static HashMap<String , ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForProduct
            = new LinkedHashMap<String , ArrayList<StoreItemDetailsListCategoryInfo>>();

    public static HashMap<String , ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForService
            = new LinkedHashMap<String , ArrayList<StoreItemDetailsListCategoryInfo>>();


    ImageView iv_business_pic, ivBack;
    TextView tv_professionName, tv_businessName, tv_address, tv_ratingValue, tv_ratingCount
            , tv_commentsCount, tv_open_and_close_time, tv_description, tv_licence, tv_comment;


    public static TextView tv_product_item_count;
    public static TextView tv_product_select_bill_amount;
    public static TextView tv_service_item_count;
    public static TextView tv_service_select_bill_amount;
    public static TextView tv_total_service_time;

    public static TextView tv_order;

    String time_zone ="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_service_detail_and_order);
        viewModel = ViewModelProviders.of(this).get(ProfessionalDetailsWithProductsAndServicesViewModel.class);
        activity =  this;
        professionalId = getIntent().getStringExtra("professionalId");
        bussinessId = getIntent().getStringExtra("bussinessId");

        initViews();
        setOnClickListener();
        getProfessionalDetailsData();
        getProductAndServiceList();

    }



    private void initViews() {
        iv_business_pic = findViewById(R.id.iv_business_pic);
        tv_professionName = findViewById(R.id.tv_professionName);
        tv_businessName = findViewById(R.id.tv_businessName);
        tv_address = findViewById(R.id.tv_address);
        tv_ratingValue = findViewById(R.id.tv_ratingValue);
        tv_ratingCount = findViewById(R.id.tv_ratingCount);
        tv_commentsCount = findViewById(R.id.tv_commentsCount);
        tv_open_and_close_time = findViewById(R.id.tv_open_and_close_time);
        tv_description = findViewById(R.id.tv_description);
        tv_licence = findViewById(R.id.tv_licence);
        tv_comment = findViewById(R.id.tv_comment);
        tv_product_item_count = findViewById(R.id.tv_product_item_count);
        tv_service_item_count = findViewById(R.id.tv_service_item_count);
        tv_total_service_time = findViewById(R.id.tv_total_service_time);
        tv_product_select_bill_amount = findViewById(R.id.tv_product_select_bill_amount);
        tv_service_select_bill_amount = findViewById(R.id.tv_service_select_bill_amount);
        tv_order = findViewById(R.id.tv_order);
        ivBack = findViewById(R.id.ivBack);


        tabs = findViewById(R.id.activities_tabs);
        viewPager = findViewById(R.id.viewPager);
        tabs.setupWithViewPager(viewPager);


        Calendar cal = Calendar.getInstance();
        time_zone = cal.getTimeZone().toString();

    }


    private void setOnClickListener() {
        tv_licence.setOnClickListener(this);
        tv_comment.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }



    private void getProfessionalDetailsData() {
        viewModel.getProfessionalDetails(this, professionalId, time_zone).observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject it) {

                if(it!= null){

                    if(it.has("status") && it.get("status").getAsString().equals("200")){

                        if(it.has("data") && it.get("data") instanceof JsonObject){
                            JsonObject dataObj = it.getAsJsonObject("data");

                            if(dataObj.has("name") && !dataObj.get("name").isJsonNull()){
                                tv_professionName.setText(dataObj.get("name").getAsString());
                            }

                            if(dataObj.has("bussiness_name") && !dataObj.get("bussiness_name").isJsonNull()){
                                tv_businessName.setText(dataObj.get("bussiness_name").getAsString());
                            }


                            if(dataObj.has("profile_pic") && !dataObj.get("profile_pic").isJsonNull()){
                                Glide.with(OrderProductsAndServicesActivity.this)
                                        .load(dataObj.get("profile_pic").getAsString())
                                        .apply(new RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                                        .into(iv_business_pic);
                            }


                            if(dataObj.has("bussiness_info") && dataObj.get("bussiness_info") instanceof JsonObject){
                                JsonObject businessInfoObj = dataObj.getAsJsonObject("bussiness_info");

                                if(businessInfoObj.has("currency_symbol")
                                        && !businessInfoObj.get("currency_symbol").isJsonNull()){
                                    currencySymbol = businessInfoObj.get("currency_symbol").getAsString();
                                }

                            }


                            if(dataObj.has("ratingAverage") && !dataObj.get("ratingAverage").isJsonNull()){

                                tv_ratingValue.setText(""+dataObj.get("ratingAverage").getAsInt());
                            }

                            if(dataObj.has("totalRating") && !dataObj.get("totalRating").isJsonNull()){
                                tv_ratingCount.setText("("+dataObj.get("totalRating").getAsInt() + "Ratings)");
                            }

                            if(dataObj.has("totalComment") && !dataObj.get("totalComment").isJsonNull()){
                                tv_commentsCount.setText(""+dataObj.get("totalComment").getAsInt() + " Comments");
                                tv_comment.setText(""+dataObj.get("totalComment").getAsInt() + " Comments");
                            }



                            if(dataObj.has("bussiness_info") && dataObj.get("bussiness_info") instanceof JsonObject){
                                JsonObject businessObj = dataObj.getAsJsonObject("bussiness_info");

                                if(businessObj.has("open_time") && businessObj.has("close_time")){

                                    String openTime = businessObj.get("open_time").getAsString();
                                    String closeTime = businessObj.get("close_time").getAsString();

                                    tv_open_and_close_time.setText(Utils.Companion.get12hrFormatfrom24hr(openTime)
                                            +" - " + Utils.Companion.get12hrFormatfrom24hr(closeTime));

                                }
                            }

                            if(dataObj.has("description") && !dataObj.get("description").isJsonNull()){
                                tv_description.setText(dataObj.get("description").getAsString());
                            }

                        }

                    }

                }

            }
        });


    }



    private void getProductAndServiceList(){
        viewModel.getProductAndServiceList(this, bussinessId).observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject it) {

                if(it != null){
                    allJsonData = it;

                    if(finalListHashMapForProduct!=null && finalListHashMapForProduct.size()>0)
                    {
                        finalListHashMapForProduct.clear();
                    }


                    if(finalListHashMapForService != null && finalListHashMapForService.size() >0){
                        finalListHashMapForService.clear();
                    }


                    if(it.has("status") && it.get("status").getAsString().equals("200")){

                        if(it.has("data") && it.get("data") instanceof JsonArray){
                             Type type = new TypeToken<ArrayList<ServiceAndProductListDataModal>>() {}.getType();
                             ArrayList<ServiceAndProductListDataModal> dataList = new Gson().fromJson(it.get("data"), type);


                            if(dataList.size() >0){
                                serviceAndProductList.addAll(dataList);

                                pagerAdapter = new ServiceAndProductOrderPagerAdapter(getSupportFragmentManager());
                                viewPager.setOffscreenPageLimit(serviceAndProductList.size());   // Used to maintain state of each page in viewpager


                                for(int i = 0; i < serviceAndProductList.size(); i++){
                                    categoryName = serviceAndProductList.get(i).getCategoryName();
                                    categoryType = serviceAndProductList.get(i).getCategoryType();
                                    categoryList = serviceAndProductList.get(i).getInfo();

                                    pagerAdapter.addFragment(new ServiceAndProductOrderMenuFragment(activity, categoryList, categoryType, currencySymbol), categoryName);
                                }

                                viewPager.setAdapter(pagerAdapter);
                            }
                        }
                    }

                }
            }
        });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.tv_licence:
                Intent i = new Intent(this, LicenceListActivity.class);
                startActivity(i);
                break;
            case R.id.tv_comment:
                Intent i1 = new Intent(this, CommentListActivity.class);
                i1.putExtra("professionalId", professionalId);
                startActivity(i1);
                break;
            case R.id.tv_order:
                Intent i2 = new Intent(this, OrderPlaceActivity.class);
                startActivity(i2);
                break;

        }

    }
}
