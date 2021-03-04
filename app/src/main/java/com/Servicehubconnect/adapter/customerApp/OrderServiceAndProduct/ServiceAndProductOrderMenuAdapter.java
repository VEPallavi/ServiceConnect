package com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Servicehubconnect.R;
import com.Servicehubconnect.activity.customer.OrderProductsAndServicesActivity;
import com.Servicehubconnect.callback.AddItemListener;
import com.Servicehubconnect.helper.AppConstants;
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceAndProductOrderMenuAdapter extends RecyclerView.Adapter<ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder> {
    private Activity activity;
    private ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList;
    private  HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForProduct;
    private  HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForService;
    private String categoryType, currencySymbol;
    private AddButtonClickListener addButtonClickListener;
    private IncreaseQuantityClickListener increaseQuantityClickListener;
    private DecreaseQuantityClickListener decreaseQuantityClickListener;

    AddItemListener addItemListener;


    public ServiceAndProductOrderMenuAdapter(Activity activity, ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList
            , HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForProduct
            , HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMapForService
            , String categoryType, String currencySymbol) {

        this.activity = activity;

        this.storeItemDetailsList = storeItemDetailsList;

        this.finalListHashMapForProduct = finalListHashMapForProduct;

        this.finalListHashMapForService = finalListHashMapForService;

        this.categoryType = categoryType;

        this.currencySymbol = currencySymbol;

        this.addItemListener = addItemListener;

    }



    @NonNull
    @Override
    public ServiceAndProductOrderMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item_row_list_product_or_service, parent, false);
        ServiceAndProductOrderMenuViewHolder orderMenuViewHolder = new ServiceAndProductOrderMenuViewHolder(view);
        return orderMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAndProductOrderMenuViewHolder holder, int position) {

        //        categoryType : "product' for product   & "service' for service

         if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

            holder.tv_name.setText(storeItemDetailsList.get(position).getName());
            holder.tv_price.setText(currencySymbol +""+storeItemDetailsList.get(position).getPrice());
            holder.tv_descp.setText(storeItemDetailsList.get(position).getDescription());

             Glide.with(activity)
                     .load(storeItemDetailsList.get(position).getImage().toString())
                     .apply(new RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                     .into(holder.iv_business_image);


             // Todo
             if(storeItemDetailsList.get(position).getIsSelectedServiceCount() ==0){
                 holder.cl_item_minus_count_plus.setVisibility(View.GONE);

                 holder.tv_add.setVisibility(View.VISIBLE);

                 holder.tv_item_count.setText("0");
             }
             else{
                 holder.cl_item_minus_count_plus.setVisibility(View.VISIBLE);

                 holder.tv_add.setVisibility(View.GONE);

                 holder.tv_item_count.setText(""+ storeItemDetailsList.get(position).getIsSelectedServiceCount() );
             }

            // addItemListener.addItemListener(storeItemDetailsList.get(position), position, categoryType);






             // Todo
             holder.tv_add.setOnClickListener(new AddOrderClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

     //       holder.iv_minus.setOnClickListener(new DecreaseOrderQuantityClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

             holder.iv_plus.setOnClickListener(new IncreaseOrderQuantityClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

        }


        else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

            holder.tv_name.setText(storeItemDetailsList.get(position).getName());
            holder.tv_price.setText(currencySymbol +""+storeItemDetailsList.get(position).getPrice());
            holder.tv_descp.setText(storeItemDetailsList.get(position).getDescription());

             Glide.with(activity)
                     .load(storeItemDetailsList.get(position).getImage().toString())
                     .apply(new RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                     .into(holder.iv_business_image);


             if(storeItemDetailsList.get(position).getIsSelectedProductCount() ==0){
                 holder.cl_item_minus_count_plus.setVisibility(View.GONE);

                 holder.tv_add.setVisibility(View.VISIBLE);

                 holder.tv_item_count.setText("0");
             }
             else{
                 holder.cl_item_minus_count_plus.setVisibility(View.VISIBLE);

                 holder.tv_add.setVisibility(View.GONE);

                 holder.tv_item_count.setText(""+ storeItemDetailsList.get(position).getIsSelectedProductCount());
             }



             holder.tv_add.setOnClickListener(new AddOrderClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

             holder.iv_minus.setOnClickListener(new DecreaseOrderQuantityClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

             holder.iv_plus.setOnClickListener(new IncreaseOrderQuantityClickListener(holder, storeItemDetailsList.get(position), position, categoryType));

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (storeItemDetailsList != null) {

            return storeItemDetailsList.size();

        } else {

            return 0;
        }
    }


    /**
     * Add Button click behavior
     **/
    public interface AddButtonClickListener {

        void onAddButtonClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType);

    }

    public void setOnAddButtonClickListener(AddButtonClickListener addButtonClickListener) {

        this.addButtonClickListener = addButtonClickListener;

    }


    private class AddOrderClickListener implements View.OnClickListener {

        private ServiceAndProductOrderMenuViewHolder viewHolder;

        private StoreItemDetailsListCategoryInfo storeItemDetailsListModel;

        private int position;
        private String categoryType;


        public AddOrderClickListener(ServiceAndProductOrderMenuViewHolder viewHolder
                , StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType) {

            this.viewHolder = viewHolder;

            this.storeItemDetailsListModel = storeItemDetailsListModel;

            this.position = position;

            this.categoryType = categoryType;
        }

        @Override
        public void onClick(View v) {


            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);
                }
                else if(storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);
                }
                else if(!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }
                else if(storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }


                // Todo
              //  storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);

            }

            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){


                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);
                }
                else if(storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);
                }
                else if(!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }
                else if(storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }

              //  storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);
            }

            addButtonClickListener.onAddButtonClickListener(viewHolder, storeItemDetailsListModel, position, categoryType);

        }
    }






    /**
     * Increase Quantity click behavior
     **/
    public interface IncreaseQuantityClickListener {

        void onIncreaseQuantityClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType);
    }

    public void setOnIncreaseQuantityClickListener(IncreaseQuantityClickListener increaseQuantityClickListener) {

        this.increaseQuantityClickListener = increaseQuantityClickListener;
    }

    private class IncreaseOrderQuantityClickListener implements View.OnClickListener {

        private ServiceAndProductOrderMenuViewHolder viewHolder;

        private StoreItemDetailsListCategoryInfo storeItemDetailsListModel;

        private int position;

        private String categoryType;


        public IncreaseOrderQuantityClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType) {

            this.viewHolder = viewHolder;

            this.storeItemDetailsListModel = storeItemDetailsListModel;

            this.position = position;

            this.categoryType = categoryType;

        }

        @Override
        public void onClick(View v) {


            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);
                }
                else if(storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);
                }
                else if(!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }
                else if(storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }


                // Todo
            //    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()+1);

            }

            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){

                if (!storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);
                }
                else if(storeItemDetailsListModel.getIsSize() && !storeItemDetailsListModel.getIsExtraPackage()){
                    storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);
                }
                else if(!storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }
                else if(storeItemDetailsListModel.getIsSize() && storeItemDetailsListModel.getIsExtraPackage()){

                }

                // Todo
              //  storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()+1);

            }

            increaseQuantityClickListener.onIncreaseQuantityClickListener(viewHolder, storeItemDetailsListModel, position, categoryType);
        }
    }





    /**
     * Decrease Quantity click behavior
     **/
    public interface DecreaseQuantityClickListener {

        void onDecreaseQuantityClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType);
    }

    public void setOnDecreaseQuantityClickListener(DecreaseQuantityClickListener decreaseQuantityClickListener) {

        this.decreaseQuantityClickListener = decreaseQuantityClickListener;
    }

    private class DecreaseOrderQuantityClickListener implements View.OnClickListener {

        private ServiceAndProductOrderMenuViewHolder viewHolder;

        private StoreItemDetailsListCategoryInfo storeItemDetailsListModel;

        private int position;

        private String categoryType;

        public DecreaseOrderQuantityClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position, String categoryType) {

            this.viewHolder = viewHolder;

            this.storeItemDetailsListModel = storeItemDetailsListModel;

            this.position = position;

            this.categoryType = categoryType;

        }

        @Override
        public void onClick(View v) {

            if(categoryType.equals(AppConstants.CATEGORY_TYPE_SERVICE)){

                    // Todo
//                if(storeItemDetailsList.get(position).getIsSelectedServiceCount() > 1){
//                    storeItemDetailsListModel.setIsSelectedServiceCount(storeItemDetailsList.get(position).getIsSelectedServiceCount()-1);
//                }

            }

            else if(categoryType.equals(AppConstants.CATEGORY_TYPE_PRODUCT)){
                // Todo

                if(storeItemDetailsList.get(position).getIsSelectedProductCount() >=1){
                    storeItemDetailsListModel.setIsSelectedProductCount(storeItemDetailsList.get(position).getIsSelectedProductCount()-1);
                }
            }
            decreaseQuantityClickListener.onDecreaseQuantityClickListener(viewHolder, storeItemDetailsListModel, position, categoryType);
        }
    }




    public class ServiceAndProductOrderMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_business_image;
        TextView tv_price, tv_name;
        TextView tv_descp;
        TextView tv_add;
        ConstraintLayout cl_item_minus_count_plus, cl_add_product;
        ImageView iv_minus;
        TextView tv_item_count;
        ImageView iv_plus;

//        ConstraintLayout cl_add_service, cl_item_minus_count_plus_service;
//        TextView tv_add_service, tv_item_count_service;
//        ImageView iv_minus_service, iv_plus_service;



        public ServiceAndProductOrderMenuViewHolder(@NonNull View view) {
            super(view);

            iv_business_image = view.findViewById(R.id.iv_business_image);
            tv_name = view.findViewById(R.id.tv_name);
            tv_price = view.findViewById(R.id.tv_price);
            tv_descp = view.findViewById(R.id.tv_descp);
            cl_add_product = view.findViewById(R.id.cl_add_product);
            tv_add = view.findViewById(R.id.tv_add);
            cl_item_minus_count_plus = view.findViewById(R.id.cl_item_minus_count_plus);
            iv_minus = view.findViewById(R.id.iv_minus);
            tv_item_count = view.findViewById(R.id.tv_item_count);
            iv_plus = view.findViewById(R.id.iv_plus);


//            cl_add_service = view.findViewById(R.id.cl_add_service);
//            tv_add_service = view.findViewById(R.id.tv_add_service);
//            cl_item_minus_count_plus_service = view.findViewById(R.id.cl_item_minus_count_plus_service);
//            iv_minus_service = view.findViewById(R.id.iv_minus_service);
//            tv_item_count_service = view.findViewById(R.id.tv_item_count_service);
//            iv_plus_service = view.findViewById(R.id.iv_plus_service);

        }
    }



}




//           if(finalListHashMap.containsKey(storeItemDetailsList.get(position).getId()))
//                   {
//                   holder.cl_item_minus_count_plus.setVisibility(View.VISIBLE);
//
//                   holder.tv_add.setVisibility(View.GONE);
//
//                   holder.tv_item_count.setText(""+finalListHashMap.get(storeItemDetailsList.get(position).getId()).size());
//                   }else {
//
//                   holder.cl_item_minus_count_plus.setVisibility(View.GONE);
//
//                   holder.tv_add.setVisibility(View.VISIBLE);
//
//                   holder.tv_item_count.setText("0");
//                   }






//            if(finalListHashMapForService.containsKey(storeItemDetailsList.get(position).getId()))
//                    {
//                    holder.cl_item_minus_count_plus.setVisibility(View.VISIBLE);
//
//                    holder.tv_add.setVisibility(View.GONE);
//
//                    holder.tv_item_count.setText(""+finalListHashMapForService.get(storeItemDetailsList.get(position).getId()).size());
//                    }else {
//
//                    holder.cl_item_minus_count_plus.setVisibility(View.GONE);
//
//                    holder.tv_add.setVisibility(View.VISIBLE);
//
//                    holder.tv_item_count.setText("0");
//                    }