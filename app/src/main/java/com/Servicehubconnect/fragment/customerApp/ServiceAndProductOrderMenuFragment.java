package com.Servicehubconnect.fragment.customerApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.Servicehubconnect.dialogs.TwoButtonActionDialog;
import com.Servicehubconnect.helper.AppConstants;
import com.Servicehubconnect.helper.Utils;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class ServiceAndProductOrderMenuFragment extends Fragment {
    RecyclerView rv_service_and_order_menu;
    LinearLayoutManager linearLayoutManager;
    TextView c;
    int val;
    private Activity activity;
    private ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList;
    ServiceAndProductOrderMenuAdapter serviceAndProductOrderMenuAdapter;
    String categoryType, currencySymbol;
    private RadioGroup rgSize;
    private Button btnNext;
    private AlertDialog infoSizeSelectionDialog;
    private LinearLayout lnLayoutRemoveEditItemContainer;
    private Button btnClose;
    private ImageView imgFoodType;
    private TextView txtFoodName;
    private TextView txtFoodSize;
    private TextView txtExtras;
    private TextView txtFoodDescription;
    private Button btnShowMoreOrLess;
    private ImageView imgEditItem;
    private ImageView imgRemoveItem;
    private AlertDialog editOrRemoveItemDialog;
    private int editIndex=0;
    private TwoButtonActionDialog twoButtonActionDialog;

    int positionSelectedItem;
    Boolean isAdd;



    @SuppressLint("ValidFragment")
    public ServiceAndProductOrderMenuFragment(Activity activity, ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList
            , String categoryType, String currencySymbol) {
        this.activity = activity;
        this.storeItemDetailsList = storeItemDetailsList;
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

        serviceAndProductOrderMenuAdapter = new ServiceAndProductOrderMenuAdapter(getActivity(), storeItemDetailsList
                , OrderProductsAndServicesActivity.finalListHashMapForProduct, OrderProductsAndServicesActivity.finalListHashMapForService, categoryType, currencySymbol);


        serviceAndProductOrderMenuAdapter.setOnAddButtonClickListener(new AddOrderButtonClickListener());

        serviceAndProductOrderMenuAdapter.setOnIncreaseQuantityClickListener(new IncreaseOrderQuantityClickListener());

        serviceAndProductOrderMenuAdapter.setOnDecreaseQuantityClickListener(new DecreaseOrderQuantityClickListener());


        rv_service_and_order_menu.setAdapter(serviceAndProductOrderMenuAdapter);
    }




    private class AddOrderButtonClickListener implements ServiceAndProductOrderMenuAdapter.AddButtonClickListener {

        @Override
        public void onAddButtonClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType)
        {

                 ///  CATEGORY_TYPE_SERVICE

             if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel,categoryType);

                }
                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);



                   // addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);


                  //  addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);

                    positionSelectedItem = position;
                    isAdd = true;

                Intent i = new Intent(activity, ExtraPackageListActivity.class);

                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());

                i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getPrice().toString());

                startActivityForResult(i, 1);



                 //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
            }




            ///  CATEGORY_TYPE_PRODUCT

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                      StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                      showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);



                   // addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                      showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);




                    //addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }
                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);

                positionSelectedItem = position;
                isAdd = true;


                Intent i = new Intent(activity, ExtraPackageListActivity.class);

                i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

              //  i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getStoreCategoriesItemId());

                i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getPrice().toString());

                startActivityForResult(i, 1);

                    //   addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }

            }
        }
    }






    private class IncreaseOrderQuantityClickListener implements ServiceAndProductOrderMenuAdapter.IncreaseQuantityClickListener{


        @Override
        public void onIncreaseQuantityClickListener(ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel
                , int position, String categoryType)

        {

            // TODO

                  ///   CATEGORY_TYPE_SERVICE
            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }

                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);
                }
//
                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);


                  //  addListToFinalHashMap(storeItemDetailsListModel, categoryType);
                }
//
                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);

                      positionSelectedItem = position;
                      isAdd = true;

                    Intent i = new Intent(activity, ExtraPackageListActivity.class);

                    i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                    i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

                    i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getId());

                    i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getPrice());

                    startActivityForResult(i, 1);

                }
//
//
//
            }



                 ////     CATEGORY_TYPE_PRODUCT
             if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                }

                else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);
                }

                else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    showSizePopup(vStoreItemDetailsListModel , false, categoryType, position);


                  //  addListToFinalHashMap(storeItemDetailsListModel, categoryType);
                }

                else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {
                    StoreItemDetailsListCategoryInfo vStoreItemDetailsListModel = getClonedvStoreItemDetailsListModelObject(storeItemDetailsListModel);

                    String strJsonStoreItemDetailsLModel = new Gson().toJson(vStoreItemDetailsListModel);

                    positionSelectedItem = position;
                    isAdd = true;

                    Intent i = new Intent(activity, ExtraPackageListActivity.class);

                    i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                    i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

                    i.putExtra(AppConstants.ITEM_ID, vStoreItemDetailsListModel.getId());

                    i.putExtra(AppConstants.SIZE_PRICE , vStoreItemDetailsListModel.getPrice());

                    startActivityForResult(i, 1);



                }

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

//                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//
//                    if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemDetailsListModel.getId())) {
//
//                        OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId()).remove(0);
//
//                        if (storeItemDetailsListModel.isSelectedServiceCount() == 0) {
//                            OrderProductsAndServicesActivity.finalListHashMapForService.remove(storeItemDetailsListModel);
//
//                        }
////                        else
////                        {
////                            OrderProductsAndServicesActivity.finalListHashMapForService
////                                    .remove(OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel));
////
////
////                            Log.e("<<<<", "<<<  decrease <<"+OrderProductsAndServicesActivity.finalListHashMapForService.size());
////
////                        }
//
//                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();
//                        setSelectedItemCountandPrice(categoryType);
//                    }
//                }
//                else {
//                     showModifyItemPopup(storeItemDetailsListModel, categoryType);
//                }


            }


            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(storeItemDetailsListModel.getId())) {

                        OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemDetailsListModel.getId()).remove(0);

                        if (storeItemDetailsListModel.isSelectedProductCount() == 0) {
                            OrderProductsAndServicesActivity.finalListHashMapForProduct.remove(storeItemDetailsListModel);

                        }
                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                        setSelectedItemCountandPrice(categoryType);
                    }
                }

                else {
                     showModifyItemPopup(storeItemDetailsListModel, categoryType, position);
                }











  /*              if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                    if (OrderProductsAndServicesActivity.finalListHashMap.containsKey(storeItemDetailsListModel.getId())) {

                        OrderProductsAndServicesActivity.finalListHashMap.get(storeItemDetailsListModel.getId()).remove(0);

                        if (OrderProductsAndServicesActivity.finalListHashMap.get(storeItemDetailsListModel.getId()).size() == 0) {
                            OrderProductsAndServicesActivity.finalListHashMap.remove(storeItemDetailsListModel.getId());

                        }

                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                        setSelectedItemCountandPrice(categoryType);
                    }

                }

                else {
                    // showModifyItemPopup(storeItemDetailsListModel);
                }*/
            }
        }


    }




    private void showModifyItemPopup(StoreItemDetailsListCategoryInfo storeItemModel, String categoryType, int position) {

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_Dialog);

            LayoutInflater inflater = getLayoutInflater();

            View dialogLayout = inflater.inflate(R.layout.customer_pop_up_window_edit_or_remove_item, null);

            initEditRemovePopupUi(dialogLayout, categoryType);

            if (lnLayoutRemoveEditItemContainer.getChildCount() > 0) {

                lnLayoutRemoveEditItemContainer.removeAllViews();
            }

            if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemModel.getId())) {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = new ArrayList<>();

                storeItemDetailsListModels = OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemModel.getId());

                LayoutInflater layoutInflater = activity.getLayoutInflater();

                if (storeItemDetailsListModels != null && storeItemDetailsListModels.size() > 0) {

                    for (int index = 0; index < storeItemDetailsListModels.size(); index++) {

                        View editRemoveItemRow = layoutInflater.inflate(R.layout.customer_row_remove_or_edit_item, lnLayoutRemoveEditItemContainer, false);

                        txtFoodName = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_name);

                        txtFoodSize = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_size);

                        txtExtras = (TextView) editRemoveItemRow.findViewById(R.id.txt_extras);

                        txtFoodDescription = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_description);

                        btnShowMoreOrLess = (Button) editRemoveItemRow.findViewById(R.id.btn_show_more_or_less);

                        imgEditItem = (ImageView) editRemoveItemRow.findViewById(R.id.img_edit_item);

                        imgRemoveItem = (ImageView) editRemoveItemRow.findViewById(R.id.img_remove_item);

                        editRemoveItemRow.setTag(Integer.toString(index));


                        if (!TextUtils.isEmpty(storeItemModel.getName())) {

                            txtFoodName.setText(storeItemModel.getName());

                        }



                        if (storeItemDetailsListModels.get(index).getSizePriceDuration() != null
                                && storeItemDetailsListModels.get(index).getSizePriceDuration().size() > 0) {
                            String size = "";
                            for (int k = 0; k < storeItemDetailsListModels.get(index).getSizePriceDuration().size(); k++) {
                                if (storeItemDetailsListModels.get(index).getSizePriceDuration().get(k).isSelected()) {
                                    size = storeItemDetailsListModels.get(index).getSizePriceDuration().get(k).getSize();
                                }
                            }
                            if (size.equalsIgnoreCase("")) {
                                txtFoodSize.setVisibility(View.GONE);
                            } else {
                                txtFoodSize.setText("(" + size + ")");
                            }
                        } else {
                            txtFoodSize.setVisibility(View.GONE);
                        }


                        txtFoodDescription.setText(storeItemModel.getDescription());

                        if(!storeItemModel.getIsExtraPackage())
                        {
                            txtExtras.setText("No Extra Package");
                        }


                        imgRemoveItem.setOnClickListener(new RemoveItemConfirmationDialog(index, storeItemDetailsListModels.get(index).getId(), categoryType, position));

                        imgEditItem.setOnClickListener(new EditItemConfirmationDialog(index, storeItemDetailsListModels.get(index).getId(), categoryType, position));


                        lnLayoutRemoveEditItemContainer.addView(editRemoveItemRow);
                    }
                }

            }


            editOrRemoveItemDialog = builder.create();

            editOrRemoveItemDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            editOrRemoveItemDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            editOrRemoveItemDialog.setView(dialogLayout, 0, 0, 0, 0);

            editOrRemoveItemDialog.setCanceledOnTouchOutside(true);

            editOrRemoveItemDialog.setCancelable(true);

            WindowManager.LayoutParams params = editOrRemoveItemDialog.getWindow().getAttributes();

            params.gravity = Gravity.BOTTOM;

            builder.setView(dialogLayout);

            editOrRemoveItemDialog.show();

        }


        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_Dialog);

            LayoutInflater inflater = getLayoutInflater();

            View dialogLayout = inflater.inflate(R.layout.customer_pop_up_window_edit_or_remove_item, null);

            initEditRemovePopupUi(dialogLayout, categoryType);

            if (lnLayoutRemoveEditItemContainer.getChildCount() > 0) {

                lnLayoutRemoveEditItemContainer.removeAllViews();
            }

            if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(storeItemModel.getId())) {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = new ArrayList<>();

                storeItemDetailsListModels = OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemModel.getId());

                LayoutInflater layoutInflater = activity.getLayoutInflater();

                if (storeItemDetailsListModels != null && storeItemDetailsListModels.size() > 0) {

                    for (int index = 0; index < storeItemDetailsListModels.size(); index++) {

                        View editRemoveItemRow = layoutInflater.inflate(R.layout.customer_row_remove_or_edit_item, lnLayoutRemoveEditItemContainer, false);

                        txtFoodName = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_name);

                        txtFoodSize = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_size);

                        txtExtras = (TextView) editRemoveItemRow.findViewById(R.id.txt_extras);

                        txtFoodDescription = (TextView) editRemoveItemRow.findViewById(R.id.txt_food_description);

                        btnShowMoreOrLess = (Button) editRemoveItemRow.findViewById(R.id.btn_show_more_or_less);

                        imgEditItem = (ImageView) editRemoveItemRow.findViewById(R.id.img_edit_item);

                        imgRemoveItem = (ImageView) editRemoveItemRow.findViewById(R.id.img_remove_item);

                        editRemoveItemRow.setTag(Integer.toString(index));

                        lnLayoutRemoveEditItemContainer.addView(editRemoveItemRow);
                    }
                }

            }


            editOrRemoveItemDialog = builder.create();

            editOrRemoveItemDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            editOrRemoveItemDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            editOrRemoveItemDialog.setView(dialogLayout, 0, 0, 0, 0);

            editOrRemoveItemDialog.setCanceledOnTouchOutside(true);

            editOrRemoveItemDialog.setCancelable(true);

            WindowManager.LayoutParams params = editOrRemoveItemDialog.getWindow().getAttributes();

            params.gravity = Gravity.BOTTOM;

            builder.setView(dialogLayout);

            editOrRemoveItemDialog.show();
        }




    }






    private void showSizePopup(StoreItemDetailsListCategoryInfo storeItemDetailsListModel , Boolean edit, String categoryType, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_Dialog);

        LayoutInflater inflater = getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.pop_up_window_select_size, null);



        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            initPopUpUI(dialogLayout, storeItemDetailsListModel , edit, categoryType, position);

            for (int i = 0; i < storeItemDetailsListModel.getSizePriceDuration().size(); i++) {
                RadioButton rbn = new RadioButton(getActivity());

                rbn.setText(getSpannableString(storeItemDetailsListModel.getSizePriceDuration().get(i).getSize()
                        , "   " + OrderProductsAndServicesActivity.currencySymbol + String.format("%.2f"
                                , Double.parseDouble(String.valueOf(storeItemDetailsListModel.getSizePriceDuration().get(i).getPrice())))));

                rbn.setId(i);

                if(storeItemDetailsListModel.getSizePriceDuration().get(i).isSelected())
                {
                    rbn.setChecked(true);
                }

                rgSize.addView(rbn);

            }
        }

        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

            initPopUpUI(dialogLayout, storeItemDetailsListModel , edit, categoryType, position);

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


    private void initPopUpUI(View view, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, Boolean edit, String categoryType, int position) {

        rgSize = (RadioGroup) view.findViewById(R.id.rg_sizes);

        btnNext = (Button) view.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new ButtonSelectSizeClick(storeItemDetailsListModel , edit, categoryType, position));

    }


    private class ButtonSelectSizeClick implements View.OnClickListener {

        StoreItemDetailsListCategoryInfo storeItemDetailsListModel;
        boolean edit;
        String categoryType;
        int position;

        public ButtonSelectSizeClick(StoreItemDetailsListCategoryInfo storeItemDetailsListModel, Boolean edit, String categoryType, int position) {
            this.storeItemDetailsListModel = storeItemDetailsListModel;
            this.edit  = edit;
            this.categoryType = categoryType;
            this.position = position;
        }

        @Override
        public void onClick(View v) {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                int selectedRadioButtonId = rgSize.getCheckedRadioButtonId();

                if (selectedRadioButtonId == -1) {

                    Toast.makeText(activity, "Please select size.", Toast.LENGTH_SHORT).show();

                    return;

                }
                else {

                    String sizePrice = "";

                    if(storeItemDetailsListModel.getIsSize() && !edit
                            || storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage())

                    {
                        for (int i = 0; i < storeItemDetailsListModel.getSizePriceDuration().size(); i++) {
                            if (selectedRadioButtonId == i) {

                                storeItemDetailsListModel.getSizePriceDuration().get(i).setSelected(true);

                            } else {

                                storeItemDetailsListModel.getSizePriceDuration().get(i).setSelected(false);
                            }
                        }
                    }



                    sizePrice = storeItemDetailsListModel.getSizePriceDuration().get(selectedRadioButtonId).getPrice().toString();


                    if (storeItemDetailsListModel.getIsExtraPackage()) {

                        String strJsonStoreItemDetailsLModel = new Gson().toJson(storeItemDetailsListModel);

                        positionSelectedItem = position;
                        isAdd = true;

                        Intent i = new Intent(activity, ExtraPackageListActivity.class);

                        i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                        i.putExtra(AppConstants.ITEM_ID, storeItemDetailsListModel.getId());

                        i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

                        //  i.putExtra(AppConstants.IS_ITEM_EDIT, edit);

                           i.putExtra(AppConstants.SIZE_NAME , storeItemDetailsListModel.getSizePriceDuration().get(selectedRadioButtonId).getSize());

                        i.putExtra(AppConstants.SIZE_PRICE , sizePrice);

                        startActivityForResult(i, 1);

                    } else {

                        if (!edit) {
                            positionSelectedItem = position;
                            isAdd = true;
                            addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                        }
                        else {

                             addEditedListToFinalHashMap(storeItemDetailsListModel, categoryType);
                        }

                        infoSizeSelectionDialog.dismiss();
                    }
                }

            }


            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT))

            {
                int selectedRadioButtonId = rgSize.getCheckedRadioButtonId();

                if (selectedRadioButtonId == -1) {

                    Toast.makeText(activity, "Please select size.", Toast.LENGTH_SHORT).show();

                    return;

                } else {

                    String sizePrice = "";

                    if(storeItemDetailsListModel.getIsSize() && !edit || storeItemDetailsListModel.getIsSize()
                            && !storeItemDetailsListModel.getIsExtraPackage()) {

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

                        positionSelectedItem = positionSelectedItem;
                        isAdd = true;

                        Intent i = new Intent(activity, ExtraPackageListActivity.class);

                        i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                        i.putExtra(AppConstants.ITEM_ID, storeItemDetailsListModel.getId());

                        i.putExtra(AppConstants.CATEGORY_TYPE, categoryType);

                        //  i.putExtra(AppConstants.IS_ITEM_EDIT, edit);

                           i.putExtra(AppConstants.SIZE_NAME , storeItemDetailsListModel.getSizePrice().get(selectedRadioButtonId).getSize());

                        i.putExtra(AppConstants.SIZE_PRICE , sizePrice);

                        startActivityForResult(i, 1);

                    } else {

                        if (!edit) {

                            addListToFinalHashMap(storeItemDetailsListModel, categoryType);

                        } else {

                            // addEditedListToFinalHashMap(storeItemDetailsListModel, categoryType);
                        }

                        infoSizeSelectionDialog.dismiss();
                    }
                }

            }


        }
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(infoSizeSelectionDialog != null){
            infoSizeSelectionDialog.dismiss();
        }

        if(requestCode == 1){

            if(resultCode == 1){
                StoreItemDetailsListCategoryInfo storeItemDetailsListModel = new Gson().fromJson(data.getStringExtra(AppConstants.STORE_ITEM_DETAILS)
                        , StoreItemDetailsListCategoryInfo.class);

                boolean isEdit = data.getBooleanExtra(AppConstants.IS_ITEM_EDIT,false);
                String categoryType =  data.getStringExtra(AppConstants.CATEGORY_TYPE);


                if(storeItemDetailsListModel != null){

                    if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){
                        if(isAdd){
                            storeItemDetailsListModel.setSelectedServiceCount(storeItemDetailsList.get(positionSelectedItem).getIsSelectedServiceCount()+1);

                        }

                        serviceAndProductOrderMenuAdapter.notifyDataSetChanged();


                    }

                    else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
                        if(isAdd){
                            storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(positionSelectedItem).getIsSelectedProductCount()+1);

                        }
                        else{
                            if(storeItemDetailsList.get(positionSelectedItem).getIsSelectedProductCount() >=1){
                                storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(positionSelectedItem).getIsSelectedProductCount()-1);
                            }

                        }
                         serviceAndProductOrderMenuAdapter.notifyDataSetChanged();
                    }

                }


                if(!isEdit) {

                    addListToFinalHashMap(storeItemDetailsListModel, categoryType);
                }else {

                    addEditedListToFinalHashMap(storeItemDetailsListModel, categoryType);
                }

            }

        }


    }




    private void addEditedListToFinalHashMap(StoreItemDetailsListCategoryInfo storeItemDetailsListModel, String categoryType) {
        {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

                if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(storeItemDetailsListModel.getId())) {

                    ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemDetailsListModel.getId());

                    storeItemDetailsListModels.remove(editIndex);

                    storeItemDetailsListModels.add(editIndex,storeItemDetailsListModel);

                    OrderProductsAndServicesActivity.finalListHashMapForProduct.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
                }

                serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                setSelectedItemCountandPrice(categoryType);
            }


            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemDetailsListModel.getId())) {

                    ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId());

                    storeItemDetailsListModels.remove(editIndex);

                    storeItemDetailsListModels.add(editIndex,storeItemDetailsListModel);

                    OrderProductsAndServicesActivity.finalListHashMapForService.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
                }

                serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                setSelectedItemCountandPrice(categoryType);

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

            if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(storeItemDetailsListModel.getId())) {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels
                        = OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemDetailsListModel.getId());

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMapForProduct.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
            }
            else {

                ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsListModels = new ArrayList<>();

                storeItemDetailsListModels.add(storeItemDetailsListModel);

                OrderProductsAndServicesActivity.finalListHashMapForProduct.put(storeItemDetailsListModel.getId(), storeItemDetailsListModels);
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

                    if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
                        totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getPrice().toString());
                    }

                    else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                        for (int k = 0; k < storeItemDetailsListModel.getSizePriceDuration().size(); k++) {

                            if (storeItemDetailsListModel.getSizePriceDuration().get(k).isSelected()) {
                                totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getSizePriceDuration().get(k).getPrice().toString());

                                break;
                            }
                        }
                    }



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



            for (Map.Entry<String, ArrayList<StoreItemDetailsListCategoryInfo>> entry : OrderProductsAndServicesActivity.finalListHashMapForProduct.entrySet()) {

                ArrayList<StoreItemDetailsListCategoryInfo> list = entry.getValue();

                totalSelectedCount = totalSelectedCount + list.size();

                for(int j = 0; j < list.size(); j++){
                    StoreItemDetailsListCategoryInfo storeItemDetailsListModel = list.get(j);


                    if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
                        totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getPrice().toString());
                    }


                    else if (storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {

                        for (int k = 0; k < storeItemDetailsListModel.getSizePrice().size(); k++) {

                            if (storeItemDetailsListModel.getSizePrice().get(k).isSelected()) {
                                totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getSizePrice().get(k).getPrice().toString());

                                break;
                            }
                        }
                    }


                    else if (storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

                        for (int k = 0; k < storeItemDetailsListModel.getSizePrice().size(); k++) {

                            if (storeItemDetailsListModel.getSizePrice().get(k).isSelected()) {
                                totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getSizePrice().get(k).getPrice().toString());

                                break;
                            }
                        }

                                   // TODO

//                        for (int z = 0; z < storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().size(); z++) {
//
//                            for (int l = 0; l < storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().size(); l++) {
//
//                                if (storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().get(l).isSelected()) {
//
//                                    totalAmount = totalAmount + storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().get(l).getPrice();
//                                }
//                            }
//                        }

                    }



                    else if (!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()) {

//                        for (int z = 0; z < storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().size(); z++) {
//
//                            for (int l = 0; l < storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().size(); l++) {
//
//                                if (storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().get(l).isSelected()) {
//
//                                    totalAmount = totalAmount + storeItemDetailsListModel.getExtraPackageDetailsResponseModel().getExtraPackageListModels().get(z).getExtraPackageDetailList().get(l).getPrice();
//                                }
//                            }
//                        }

                        totalAmount = totalAmount + convertStrToDouble(storeItemDetailsListModel.getPrice().toString());
                    }




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



    private void initEditRemovePopupUi(View view, String categoryType) {

        lnLayoutRemoveEditItemContainer = (LinearLayout) view.findViewById(R.id.ln_layout_remove_edit_item_container);

        btnClose = (Button) view.findViewById(R.id.btn_close);

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            btnClose.setOnClickListener(new CloseEditRemoveDialogPopupClickListener());
        }
        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
            btnClose.setOnClickListener(new CloseEditRemoveDialogPopupClickListener());
        }


    }


    private class CloseEditRemoveDialogPopupClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){
                if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                    editOrRemoveItemDialog.dismiss();
                }
            }
           else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
                if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                    editOrRemoveItemDialog.dismiss();
                }
            }


        }
    }




    private class RemoveItemConfirmationDialog implements View.OnClickListener {
        int index;
        String storeCategoriesItemId;
        String categoryType;
        int position;

        public RemoveItemConfirmationDialog(int index, String storeCategoriesItemId, String categoryType, int position) {
            this.index = index;
            this.storeCategoriesItemId = storeCategoriesItemId;
            this.categoryType = categoryType;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            //lnLayoutRemoveEditItemContainer.removeViewAt(index);

            showInfoDialog(false , index , storeCategoriesItemId, categoryType, position);

        }
    }



    private class EditItemConfirmationDialog implements View.OnClickListener {
        String itemId = "";
        int  index;
        String categoryType;
        int position;

        public EditItemConfirmationDialog(int index, String storeCategoriesItemId, String categoryType, int position) {
            this.itemId = storeCategoriesItemId;
            this.index = index;
            this.categoryType = categoryType;
            this.position = position;
        }


        @Override
        public void onClick(View v) {

            showInfoDialog(true ,index , itemId, categoryType, position);

        }
    }


    private void showInfoDialog(boolean b, int index, String storeCategoriesItemId, String categoryType, int position) {

        if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            String message = "";
            String okMessage = "";
            if(!b)
            {
                message = "Are you sure you want to remove this item?";
                okMessage = "Remove";
            }else {
                message = "Are you sure you want to modify this item?";
                okMessage = "Modify";
            }

            if (twoButtonActionDialog!=null && twoButtonActionDialog.isShowing()) {
                twoButtonActionDialog.dismiss();
            }else {
                twoButtonActionDialog = new TwoButtonActionDialog(activity, okMessage,
                        getString(R.string.cancel), message, new okListener( index, storeCategoriesItemId , b, categoryType, position), new cancelDialogListener());
                twoButtonActionDialog.setCancelable(false);
                twoButtonActionDialog.show();
            }


        }


        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
            String message = "";
            String okMessage = "";
            if(!b)
            {
                message = "Are you sure you want to remove this item?";
                okMessage = "Remove";
            }else {
                message = "Are you sure you want to modify this item?";
                okMessage = "Modify";
            }

            if (twoButtonActionDialog!=null && twoButtonActionDialog.isShowing()) {
                twoButtonActionDialog.dismiss();
            }else {
                twoButtonActionDialog = new TwoButtonActionDialog(activity, okMessage,
                        getString(R.string.cancel), message, new okListener( index, storeCategoriesItemId , b, categoryType, position), new cancelDialogListener());
                twoButtonActionDialog.setCancelable(false);
                twoButtonActionDialog.show();
            }


        }



    }



    private class okListener implements View.OnClickListener {

        int index;
        String itemId;
        boolean isEdit;
        String categoryType;
        int position;

        public okListener(int index, String storeCategoriesItemId , boolean isEdit, String categoryType, int position) {
            this.index = index;
            this.itemId = storeCategoriesItemId;
            this.isEdit = isEdit;
            this.categoryType = categoryType;
            this.position = position;
        }

        @Override
        public void onClick(View v) {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){



                if(twoButtonActionDialog!=null && twoButtonActionDialog.isShowing()) {

                    twoButtonActionDialog.dismiss();
                }
                if(isEdit)
                {
                    editIndex = index;
                    if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(itemId)) {

                        if (OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(index).getIsSize()) {

                            if(editOrRemoveItemDialog!=null && editOrRemoveItemDialog.isShowing())
                            {
                                editOrRemoveItemDialog.dismiss();
                            }

                            showSizePopup(OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(index)
                                    , true, categoryType, position);

                        } else if (OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(index).getIsExtraPackage()) {

                            if(editOrRemoveItemDialog!=null && editOrRemoveItemDialog.isShowing())
                            {
                                editOrRemoveItemDialog.dismiss();
                            }

                            String strJsonStoreItemDetailsLModel = new Gson().toJson(OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(index));

                            Intent i = new Intent(activity, ExtraPackageListActivity.class);

                            isAdd = false;
                            positionSelectedItem = position;

                            i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                            i.putExtra(AppConstants.ITEM_ID, OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(index).getId());

                            i.putExtra(AppConstants.IS_ITEM_EDIT, true);

                            startActivityForResult(i, 1);
                        }

                    }

                }else {

                    lnLayoutRemoveEditItemContainer.removeAllViews();

                    if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                        editOrRemoveItemDialog.dismiss();
                    }
                    if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(itemId)) {

                        OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).remove(index);

                        if (OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).size() == 0) {

                            OrderProductsAndServicesActivity.finalListHashMapForService.remove(itemId);
                        }

                    }

                    if ((OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId) != null) && (OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).size() > 0)) {

                        showModifyItemPopup(OrderProductsAndServicesActivity.finalListHashMapForService.get(itemId).get(0), categoryType, position);

                    } else {
                        if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                            editOrRemoveItemDialog.dismiss();
                        }
                    }

                    serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                    setSelectedItemCountandPrice(categoryType);

                }



            }

            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                if(twoButtonActionDialog!=null && twoButtonActionDialog.isShowing()) {

                    twoButtonActionDialog.dismiss();
                }
                if(isEdit)
                {
                    editIndex = index;
                    if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(itemId)) {

                        if (OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(index).getIsSize()) {

                            if(editOrRemoveItemDialog!=null && editOrRemoveItemDialog.isShowing())
                            {
                                editOrRemoveItemDialog.dismiss();
                            }

                            showSizePopup(OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(index) , true, categoryType, position);

                        } else if (OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(index).getIsExtraPackage()) {

                            if(editOrRemoveItemDialog!=null && editOrRemoveItemDialog.isShowing())
                            {
                                editOrRemoveItemDialog.dismiss();
                            }

                            String strJsonStoreItemDetailsLModel = new Gson().toJson(OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(index));

                            Intent i = new Intent(activity, ExtraPackageListActivity.class);

                            isAdd = false;
                            positionSelectedItem = position;

                            i.putExtra(AppConstants.STORE_ITEM_DETAILS, strJsonStoreItemDetailsLModel);

                            i.putExtra(AppConstants.ITEM_ID, OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(index).getId());

                            i.putExtra(AppConstants.IS_ITEM_EDIT, true);

                            startActivityForResult(i, 1);
                        }

                    }

                }else {

                    lnLayoutRemoveEditItemContainer.removeAllViews();

                    if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                        editOrRemoveItemDialog.dismiss();
                    }
                    if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(itemId)) {

                        OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).remove(index);

                        if (OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).size() == 0) {

                            OrderProductsAndServicesActivity.finalListHashMapForProduct.remove(itemId);
                        }

                    }

                    if ((OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId) != null) && (OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).size() > 0)) {

                        showModifyItemPopup(OrderProductsAndServicesActivity.finalListHashMapForProduct.get(itemId).get(0), categoryType, position);

                    } else {
                        if (editOrRemoveItemDialog != null && editOrRemoveItemDialog.isShowing()) {

                            editOrRemoveItemDialog.dismiss();
                        }
                    }

                    serviceAndProductOrderMenuAdapter.notifyDataSetChanged();

                    setSelectedItemCountandPrice(categoryType);

                }

            }


        }
    }



    private class cancelDialogListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (twoButtonActionDialog != null && twoButtonActionDialog.isShowing()) {
                twoButtonActionDialog.dismiss();
            }
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


    private SpannableString getSpannableString(String name, String amount, String time) {

        String foodNameAmt = name + " " + amount;

        SpannableString styledString
                = new SpannableString(foodNameAmt);

        styledString.setSpan(new RelativeSizeSpan(1f), 0, name.length()+1, 0);

        styledString.setSpan(new RelativeSizeSpan(0.8f), name.length(), foodNameAmt.length(),0);


        styledString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, name.length()+1, 0);

        styledString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.color_restaurant_address)), name.length(), foodNameAmt.length(),0);


        return styledString;

    }



//       if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){
//
//
//        if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//            if (OrderProductsAndServicesActivity.finalListHashMapForService.containsKey(storeItemDetailsListModel.getId())) {
//                OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId()).remove(0);
//
//                if (OrderProductsAndServicesActivity.finalListHashMapForService.get(storeItemDetailsListModel.getId()).size() == 0) {
//                    OrderProductsAndServicesActivity.finalListHashMapForService.remove(storeItemDetailsListModel.getId());
//
//                }
//
//                serviceAndProductOrderMenuAdapter.notifyDataSetChanged();
//
//                setSelectedItemCountandPrice(categoryType);
//            }
//        } else {
//            showModifyItemPopup(storeItemDetailsListModel, categoryType);
//        }
//
//
//    }
//
//
//            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
//
//        if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()) {
//            if (OrderProductsAndServicesActivity.finalListHashMapForProduct.containsKey(storeItemDetailsListModel.getId())) {
//                OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemDetailsListModel.getId()).remove(0);
//
//                if (OrderProductsAndServicesActivity.finalListHashMapForProduct.get(storeItemDetailsListModel.getId()).size() == 0) {
//                    OrderProductsAndServicesActivity.finalListHashMapForProduct.remove(storeItemDetailsListModel.getId());
//
//                }
//
//                serviceAndProductOrderMenuAdapter.notifyDataSetChanged();
//
//                setSelectedItemCountandPrice(categoryType);
//            }
//        } else {
//            showModifyItemPopup(storeItemDetailsListModel, categoryType);
//        }
//
//    }












}
