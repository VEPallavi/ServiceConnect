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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Servicehubconnect.R;
import com.Servicehubconnect.activity.customer.ExtraPackageListActivity;
import com.Servicehubconnect.activity.customer.OrderProductsAndServicesActivity;
import com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct.ServiceAndProductOrderMenuAdapter;
import com.Servicehubconnect.callback.AddItemListener;
import com.Servicehubconnect.callback.ItemListener;
import com.Servicehubconnect.helper.AppConstants;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class ServiceAndProductOrderMenuFragment extends Fragment implements AddItemListener {
    RecyclerView rv_service_and_order_menu;
    LinearLayoutManager linearLayoutManager;
    TextView c;
    int val;
    private Activity activity;
    private ArrayList<StoreItemDetailsListCategoryInfo> categoryInfoList;
    ServiceAndProductOrderMenuAdapter serviceAndProductOrderMenuAdapter;
    String categoryType, currencySymbol;
    private RadioGroup rgSize;
    private Button btnNext;
    private AlertDialog infoSizeSelectionDialog;



    @SuppressLint("ValidFragment")
    public ServiceAndProductOrderMenuFragment(Activity activity, ArrayList<StoreItemDetailsListCategoryInfo> categoryInfoList
            , String categoryType, String currencySymbol) {
        this.activity = activity;
        this.categoryInfoList = categoryInfoList;
        this.categoryType = categoryType;
        this.currencySymbol = currencySymbol;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_service_and_order_menu,container, false);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_service_and_order_menu = view.findViewById(R.id.rv_service_and_order_menu);
        rv_service_and_order_menu.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_service_and_order_menu.setLayoutManager(linearLayoutManager);

        serviceAndProductOrderMenuAdapter = new ServiceAndProductOrderMenuAdapter(getActivity(), categoryInfoList
                , OrderProductsAndServicesActivity.finalListHashMap, OrderProductsAndServicesActivity.finalListHashMapForService, categoryType, currencySymbol, this);


     //   serviceAndProductOrderMenuAdapter.setOnAddButtonClickListener(new AddOrderButtonClickListener());

        serviceAndProductOrderMenuAdapter.setOnIncreaseQuantityClickListener(new IncreaseOrderQuantityClickListener());

        serviceAndProductOrderMenuAdapter.setOnDecreaseQuantityClickListener(new DecreaseOrderQuantityClickListener());


        rv_service_and_order_menu.setAdapter(serviceAndProductOrderMenuAdapter);
    }

    @Override
    public void itemListener(@NotNull StoreItemDetailsListCategoryInfo dataModel, int position, @NotNull String categoryType) {

        StoreItemDetailsListCategoryInfo  storeItemDetailsListModel = dataModel;


        ///  CATEGORY_TYPE_PRODUCT

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


            if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
            else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                ///  StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                //  showSizePopup(vStoreItemDetailsListModel , false, categoryType);



                addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
            else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                //StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                //  showSizePopup(vStoreItemDetailsListModel , false, categoryType);




                addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
            else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

//                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);
//
//                Intent i = new Intent(activity, ExtraPackageListActivity.class);
//
//                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);
//
//              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());
//
//             //   i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getStoreCategoriesItemPrice());
//
//                startActivityForResult(i, 1);



                //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }

        }



        ///  CATEGORY_TYPE_SERVICE

        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                addListToFinalHashMap(storeItemDetailsListModel,categoryType);

            }
            else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                showSizePopup(vStoreItemDetailsListModel , false, categoryType);



                // addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
            else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                showSizePopup(vStoreItemDetailsListModel , false, categoryType);




                //  addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
            else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

//                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);
//
//                Intent i = new Intent(activity, ExtraPackageListActivity.class);
//
//                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);
//
//              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());
//
//             //   i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getStoreCategoriesItemPrice());
//
//                startActivityForResult(i, 1);



                //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);

            }
        }

    }


//    private class AddOrderButtonClickListener implements ServiceAndProductOrderMenuAdapter.AddButtonClickListener {
//
//        @Override
//        public void onAddButtonClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
//                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType) {
//
//
//
//                    ///  CATEGORY_TYPE_PRODUCT
//
//            if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
//
//
//                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//
//                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//
//              ///  StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//              //  showSizePopup(vStoreItemDetailsListModel , false, categoryType);
//
//
//
//                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
//
//                //StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//              //  showSizePopup(vStoreItemDetailsListModel , false, categoryType);
//
//
//
//
//                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
//
////                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
////
////                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);
////
////                Intent i = new Intent(activity, ExtraPackageListActivity.class);
////
////                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);
////
////              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());
////
////             //   i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getStoreCategoriesItemPrice());
////
////                startActivityForResult(i, 1);
//
//
//
//                 //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//
//            }
//
//
//
//                 ///  CATEGORY_TYPE_SERVICE
//
//            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){
//
//                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//
//                    addListToFinalHashMap(storeItemDetailsListModel,categoryType);
//
//                }
//                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//
//                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//                    showSizePopup(vStoreItemDetailsListModel , false, categoryType);
//
//
//
//                   // addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
//
//                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
//
//                showSizePopup(vStoreItemDetailsListModel , false, categoryType);
//
//
//
//
//                  //  addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
//
////                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);
////
////                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);
////
////                Intent i = new Intent(activity, ExtraPackageListActivity.class);
////
////                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);
////
////              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());
////
////             //   i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getStoreCategoriesItemPrice());
////
////                startActivityForResult(i, 1);
//
//
//
//                 //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);
//
//                }
//            }
//        }
//    }






    private class IncreaseOrderQuantityClickListener implements ServiceAndProductOrderMenuAdapter.IncreaseQuantityClickListener{


        @Override
        public void onIncreaseQuantityClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel
                , int position, String categoryType)

        {
                  ///   CATEGORY_TYPE_SERVICE
            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

              //  if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);

               // }


            }



                 ////     CATEGORY_TYPE_PRODUCT
            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

             //   if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);

             //   }

            }


        }


    }




    private class DecreaseOrderQuantityClickListener implements ServiceAndProductOrderMenuAdapter.DecreaseQuantityClickListener{


        @Override
        public void onDecreaseQuantityClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel
                , int position, String categoryType)


        {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

             //   if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemDetailsListModel.getId())) {

                        OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId()).remove(0);

                        if (OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId()).size() == 0) {
                            OrderProductsAndServicesActivity.finalListHashMapForService.remove(storeItemDetailsListModel.getId());

                        }

                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                        setSelectedItemCountandPrice(categoryType);
                    }
               // }


                else {
                    // showModifyItemPopup(storeItemDetailsListModel);
                }


            }



            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
                    if (OrderProductsAndServicesActivity.finalListHashMap.containsKey(storeItemDetailsListModel.getId())) {
                        OrderProductsAndServicesActivity.finalListHashMap.get(storeItemDetailsListModel.getId()).remove(0);

                        if (OrderProductsAndServicesActivity.finalListHashMap.get(storeItemDetailsListModel.getId()).size() == 0) {
                            OrderProductsAndServicesActivity.finalListHashMap.remove(storeItemDetailsListModel.getId());

                        }

                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                        setSelectedItemCountandPrice(categoryType);
                    }
                } else {
                    // showModifyItemPopup(storeItemDetailsListModel);
                }
            }



        }


    }









    private void showSizePopup(StoreItemDetailsListCategoryInfo storeItemDetailsListModel , Boolean edit, String categoryType) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_Dialog);

        LayoutInflater inflater = getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.pop_up_window_select_size, null);

        initPopUpUI(dialogLayout, storeItemDetailsListModel , edit);


        for (int i = 0; i < storeItemDetailsListModel.getSizePrice().size(); i++) {
            RadioButton rbn = new RadioButton(getActivity());

            rbn.setText(getSpannableString(storeItemDetailsListModel.getSizePrice().get(i).getSize()
                    , "   " + OrderProductsAndServicesActivity.currencySymbol + String.format("%.2f"
                            , Double.parseDouble(String.valueOf(storeItemDetailsListModel.getSizePrice().get(i).getPrice())))));

            rbn.setId(i);

            if(storeItemDetailsListModel.getSizePrice().get(i).isSelected())
            {
                rbn.setChecked(true);
            }

            rgSize.addView(rbn);

        }

        infoSizeSelectionDialog = builder.create();

        infoSizeSelectionDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        infoSizeSelectionDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        infoSizeSelectionDialog.setView(dialogLayout, 0, 0, 0, 0);

        infoSizeSelectionDialog.setCanceledOnTouchOutside(false);

        infoSizeSelectionDialog.setCancelable(true);

        WindowManager.LayoutParams params = infoSizeSelectionDialog.getWindow() .getAttributes();

        params.gravity = Gravity.BOTTOM;

        builder.setView(dialogLayout);

        infoSizeSelectionDialog.show();

    }


    private void initPopUpUI(View view, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, Boolean edit) {

        rgSize = (RadioGroup) view.findViewById(R.id.rg_sizes);

        btnNext = (Button) view.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new ButtonSelectSizeClick(storeItemDetailsListModel , edit));

    }


    private class ButtonSelectSizeClick implements View.OnClickListener {

        StoreItemDetailsListCategoryInfo storeItemDetailsListModel;
        boolean edit;

        public ButtonSelectSizeClick(StoreItemDetailsListCategoryInfo storeItemDetailsListModel, Boolean edit) {
            this.storeItemDetailsListModel = storeItemDetailsListModel;
            this.edit  = edit;
        }

        @Override
        public void onClick(View v) {

            int selectedRadioButtonId = rgSize.getCheckedRadioButtonId();

            if (selectedRadioButtonId == -1) {

                Toast.makeText(activity, "Please select size.", Toast.LENGTH_SHORT).show();

                return;

            } else {

                String sizePrice = "";

                if(storeItemDetailsListModel.getIsSize() && !edit || storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
                    for (int i = 0; i < storeItemDetailsListModel.getSizePrice().size(); i++) {
                        if (selectedRadioButtonId == i) {

                            storeItemDetailsListModel.getSizePrice().get(i).setSelected(true);

                        } else {

                            storeItemDetailsListModel.getSizePrice().get(i).setSelected(false);
                        }
                    }
                }

                sizePrice = storeItemDetailsListModel.getSizePrice().get(selectedRadioButtonId).getPrice().toString();

                if (storeItemDetailsListModel.getIsExtraPackage()) {

                    String strJsonStoreItemDetailsLModel = new Gson().toJson(storeItemDetailsListModel);

                    Intent i = new Intent(activity, ExtraPackageListActivity.class);

                    i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                    i.putExtra(AppConstants.ITEM_ID, storeItemDetailsListModel.getId());

                    i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

                  //  i.putExtra(AppConstants.IS_ITEM_EDIT, edit);

                 //   i.putExtra(AppConstants.SIZE_ID , storeItemDetailsListModel.getSizeDetailsList().get(selectedRadioButtonId).getSizeId());

                    i.putExtra(AppConstants.SIZE_PRICE , sizePrice);

                    startActivityForResult(i, 1);

                } else {

                    if (!edit) {

                        addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                    } else {

                       // addEditedListToFinalHashMap(storeItemDetailsListModel);
                    }

                    infoSizeSelectionDialog.dismiss();
                }
            }
        }
    }



    private SpannableString getSpannableString(String name, String amount) {

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

        vStoreItemDetailsListModel = new Gson().fromJson(strJsonStoreListModel, StoreItemDetailsListCategoryInfo.class);

        return vStoreItemDetailsListModel;
    }





    public void addListToFinalHashMap(StoreItemDetailsListCategoryInfo storeItemDetailsListModel, String categoryType) {

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemDetailsListModel.getId())) {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels
                        = OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId());

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMapForService.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
            }
            else {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = new ArrayList<>();

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMapForService.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
            }

            serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

            setSelectedItemCountandPrice(categoryType);
        }


        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

            if (OrderProductsAndServicesActivity.finalListHashMap.containsKey(storeItemDetailsListModel.getId())) {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels
                        = OrderProductsAndServicesActivity.finalListHashMap.get(storeItemDetailsListModel.getId());

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMap.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
            }
            else {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = new ArrayList<>();

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMap.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
            }

            serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

            setSelectedItemCountandPrice(categoryType);
        }




    }

    private void setSelectedItemCountandPrice(String categoryType) {

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            int totalSelectedCount = 0;
            double totalAmount = 0;
            int i = 0;



            for (Map.Entry<String, ArrayList<StoreItemDetailsListCategoryInfo>> entry : OrderProductsAndServicesActivity.finalListHashMapForService.entrySet()) {

                ArrayList<StoreItemDetailsListCategoryInfo> list = entry.getValue();

                totalSelectedCount = totalSelectedCount + list.size();

                for(int j = 0; j < list.size(); j++){
                    StoreItemDetailsListCategoryInfo storeItemDetailsListModel = list.get(j);

                    //   if (!storeItemDetailsListModel.is_size() && !storeItemDetailsListModel.isExtraPackage()) {
                    totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getPrice());
                    //   }
                }
                i++;
            }


            if (totalSelectedCount == 0) {

                OrderProductsAndServicesActivity.tv_service_item_count.setText("No service selected");

            } else if (totalSelectedCount == 1){

                OrderProductsAndServicesActivity.tv_service_item_count.setText("" + totalSelectedCount + " " + "Service");

            } else {

                OrderProductsAndServicesActivity.tv_service_item_count.setText("" + totalSelectedCount + " " + "Service");

            }


            OrderProductsAndServicesActivity.tv_service_select_bill_amount.setText(" " + OrderProductsAndServicesActivity.currencySymbol + String.format("%.2f", totalAmount));

        }



        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

            int totalSelectedCount = 0;
            double totalAmount = 0;
            int i = 0;



            for (Map.Entry<String, ArrayList<StoreItemDetailsListCategoryInfo>> entry : OrderProductsAndServicesActivity.finalListHashMap.entrySet()) {

                ArrayList<StoreItemDetailsListCategoryInfo> list = entry.getValue();

                totalSelectedCount = totalSelectedCount + list.size();

                for(int j = 0; j < list.size(); j++){
                    StoreItemDetailsListCategoryInfo storeItemDetailsListModel = list.get(j);

                    //   if (!storeItemDetailsListModel.is_size() && !storeItemDetailsListModel.isExtraPackage()) {
                    totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getPrice());
                    //   }
                }
                i++;
            }


            if (totalSelectedCount == 0) {

                OrderProductsAndServicesActivity.tv_product_item_count.setText("No product selected");

            } else if (totalSelectedCount == 1){

                OrderProductsAndServicesActivity.tv_product_item_count.setText("" + totalSelectedCount + " " + "Product");

            } else {

                OrderProductsAndServicesActivity.tv_product_item_count.setText("" + totalSelectedCount + " " + "Products");

            }


            OrderProductsAndServicesActivity.tv_product_select_bill_amount.setText(" " + OrderProductsAndServicesActivity.currencySymbol + String.format("%.2f", totalAmount));

        }



    }


    public Double convertStrToDouble(String strValue) {
        Double value = 0.0;
        try {
            value = Double.parseDouble(strValue);
        } catch (Exception e) {

        }
        return value;
    }

}
