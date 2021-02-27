package com.Servicehubconnect.activity.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.Servicehubconnect.R;
import com.Servicehubconnect.helper.AppConstants;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ExtraPackageListForServiceAndProductModel;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.Servicehubconnect.viewModel.customer.ExtraPackageListViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ExtraPackageListActivity extends AppCompatActivity {
    ArrayList<ExtraPackageListForServiceAndProductModel> extraServiceAndProductList = new ArrayList();
    ExtraPackageListViewModel viewModel;
    TextView txt_item_name, txt_item_price, txt_item_time, txt_selected_extras, txt_total_amount;
    LinearLayout lnLayoutExtrasContainer;
    RelativeLayout rlt_layout_add_extras;
    private StoreItemDetailsListCategoryInfo storeItemDetailsModel;
    private ExtraPackageListForServiceAndProductModel extraPackageInfoForProductAndService;
    String extraId;
    String category_type;
    double sizePrice;


    private CheckBox[] chkServiceProductName;

    private RadioGroup[] rgServiceProductName;

    private RadioButton[] rbtnServiceProductName;


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
            sizePrice =  Double.parseDouble(getIntent().getStringExtra(AppConstants.SIZE_PRICE));

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
        lnLayoutExtrasContainer = findViewById(R.id.ln_layout_extras_container);
        rlt_layout_add_extras = findViewById(R.id.rlt_layout_add_extras);
    }




    private void setData() {
        if(storeItemDetailsModel != null){
            extraId = storeItemDetailsModel.getExtraId();
            category_type = category_type;
            txt_item_name.setText(storeItemDetailsModel.getName());
        }
        txt_item_price.setText(OrderProductsAndServicesActivity.currencySymbol + String.format("%.2f", sizePrice));


        viewModel.getExtraPackageList(this, extraId, category_type).observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject it) {

                if(it!= null){

                    if(it.has("status") && it.get("status").getAsString().equals("200")){

                        if(it.has("extraInfo") && it.get("extraInfo") instanceof JsonArray) {

                            Type type = new TypeToken<ArrayList<ExtraPackageListForServiceAndProductModel>>() {}.getType();
                            ArrayList<ExtraPackageListForServiceAndProductModel> dataList = new Gson().fromJson(it.get("extraInfo"), type);

                            if(dataList.size() >0){
                                extraServiceAndProductList.addAll(dataList);
                                setExtraPackageData();
                            }
                        }
                    }
                }
            }
        });

    }

    private void setExtraPackageData() {

        if(extraServiceAndProductList != null && extraServiceAndProductList.size() >0){

            // TODO

            if(category_type.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                rgServiceProductName = new RadioGroup[extraServiceAndProductList.size()];


                for (int index = 0; index < extraServiceAndProductList.size(); index++) {

                    LinearLayout layout = new LinearLayout(this);

                    layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    layout.setOrientation(LinearLayout.VERTICAL);

                    TextView tv = new TextView(this);

                    tv.setText(extraServiceAndProductList.get(index).getExtraService());

                    tv.setTextColor(this.getResources().getColor(R.color.colorBlack));

                    tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                    tv.setTextSize(15.0f);

                    layout.addView(tv);

                     /// TODO

//                    if (extraServiceAndProductList.get(index).getIsMultiSelected()) {
//
//                        ArrayList<ExtraPackageListForServiceAndProductModel> extraPackageDetailList = extraServiceAndProductList.get(index).getExtraPackageDetailList();
//
//                        if (extraPackageDetailList != null && extraPackageDetailList.size() > 0) {
//
//                            chkServiceProductName = new CheckBox[extraPackageDetailList.size()];
//
//                            for (int indexOfPackage = 0; indexOfPackage < extraPackageDetailList.size(); indexOfPackage++) {
//
//                                chkServiceProductName[indexOfPackage] = new CheckBox(activity);
//
////                                chkFoodName[indexOfPackage].setText(extraPackageDetailList.get(indexOfPackage).getDescription() + "      $" + String.format("%.2f", extraPackageDetailList.get(indexOfPackage).getPrice()));
//                                chkServiceProductName[indexOfPackage].setText(getSpannableString(extraPackageDetailList.get(indexOfPackage).getDescription(),   "   " + OrderFoodActivity.currencySymbol + String.format("%.2f", extraPackageDetailList.get(indexOfPackage).getPrice())));
//
//                                chkServiceProductName[indexOfPackage].setTextColor(activity.getResources().getColor(R.color.color_restaurant_address));
//
//                                chkServiceProductName[indexOfPackage].setTag(extraPackageDetailList.get(indexOfPackage).getDescription());
//
//                                if (extraPackageDetailList.get(indexOfPackage).isSelected()) {
//
//                                    chkServiceProductName[indexOfPackage].setChecked(true);
//                                }
//
//                                // Set previously selected extra packages
///*
//                                    if (previouslySelectedExtraPackageList != null) {
//
//                                        for (int indexOfSelectedItems = 0; indexOfSelectedItems < previouslySelectedExtraPackageList.size(); indexOfSelectedItems++) {
//
//                                            if (previouslySelectedExtraPackageList.get(indexOfSelectedItems).getDescription()
//                                                    .equalsIgnoreCase(extraPackageDetailList.get(indexOfPackage).getDescription())) {
//
//                                                chkFoodName[indexOfPackage].setChecked(true);
//                                            }
//
//                                        }
//                                    }
//*/
//
//                                chkServiceProductName[indexOfPackage]
//                                        .setOnCheckedChangeListener(new CheckBoxClick(extraPackageDetailList.get(indexOfPackage).getExtraPackageDetailId(),
//                                                extraPackageDetailList.get(indexOfPackage).getDescription(), extraPackageDetailList.get(indexOfPackage).getPrice()));
//
//                                layout.addView(chkServiceProductName[indexOfPackage]);
//
//                            }
//                        }
//
//                    }




                    lnLayoutExtrasContainer.addView(layout);

                }

            }


             if(category_type.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                rgServiceProductName = new RadioGroup[extraServiceAndProductList.size()];


                for (int index = 0; index < extraServiceAndProductList.size(); index++) {

                    LinearLayout layout = new LinearLayout(this);

                    layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    layout.setOrientation(LinearLayout.VERTICAL);

                    TextView tv = new TextView(this);

                    tv.setText(extraServiceAndProductList.get(index).getExtraProduct());

                    tv.setTextColor(this.getResources().getColor(R.color.colorBlack));

                    tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                    tv.setTextSize(15.0f);

                    layout.addView(tv);

                    lnLayoutExtrasContainer.addView(layout);
                }

            }


        }



    }



    private class CheckBoxClick implements CompoundButton.OnCheckedChangeListener {

        String itemId;

        public CheckBoxClick(String itemId, String itemName, double price) {
            this.itemId = itemId;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          //  prepareSelectedFinalList(itemId, true, isChecked);
        }
    }


    private class RadioButtonClick implements View.OnClickListener {

        String itemId;

        public RadioButtonClick(String itemId, String itemName, double price, String packageName, int indexOfRadioGroup) {

            this.itemId = itemId;
        }

        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {

          //  prepareSelectedFinalList(itemId, false, true);
        }
    }



//    private void prepareSelectedFinalList(String strItemId, boolean isMultipleSelect, boolean isChecked)
//
//    {
//
//
//        //  extraPackageDetailsSelectedModel.getExtraPackageListModels().get(0).getExtraPackageDetailList().get(0).getExtraPackageDetailId();
//        for (int i = 0; i < extraPackageDetailsSelectedModel.getExtraPackageListModels().size(); i++) {
//
//            int jSize = extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().size();
//
//            for (int j = 0; j < jSize; j++) {
//
//                if (isMultipleSelect) {
//
//                    if (isChecked && extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).getExtraPackageDetailId().equalsIgnoreCase(strItemId) && extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).isMultiselect()) {
//
//                        extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).setSelected(true);
//                    } else if (!isChecked && extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).getExtraPackageDetailId().equalsIgnoreCase(strItemId) && extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).isMultiselect()) {
//
//                        extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).setSelected(false);
//                    }
//
//                } else {
//
//                    if (extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).getExtraPackageDetailId().equalsIgnoreCase(strItemId)) {
//
//                        if (extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).isSelected() && !extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).isMultiselect()) {
//                            extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).setSelected(false);
//                            rgFoodName[i].clearCheck();
//                        } else if (!extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).isMultiselect()) {
//
//                            extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).setSelected(true);
//                        }
//                    } else if (!extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).isMultiselect()) {
//
//                        extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).setSelected(false);
//                    }
//                }
//            }
//
//
//        }
//
//
//        calculateTotalPrice();
//
//    }
//
//
//    private void calculateTotalPrice() {
//        double totalPrize = 0.0;
//
//        totalPrize = sizePrize;
//
//        for (int i = 0; i < extraPackageDetailsSelectedModel.getExtraPackageListModels().size(); i++) {
//
//            int jSize = extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().size();
//
//            for (int j = 0; j < jSize; j++) {
//
//                if (extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).isSelected()) {
//
//                    totalPrize = totalPrize + extraPackageDetailsSelectedModel.getExtraPackageListModels().get(i).getExtraPackageDetailList().get(j).getPrice();
//                }
//            }
//        }
//
//        txtTotalAmount.setText("Item Total " + OrderFoodActivity.currencySymbol + String.format("%.2f", totalPrize));
//    }



}
