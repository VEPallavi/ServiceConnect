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
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceAndProductOrderMenuAdapter extends RecyclerView.Adapter<ServiceAndProductOrderMenuAdapter.ServiceAndProductOrderMenuViewHolder> {
    private Activity activity;
    private ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList;
    private  HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMap;
    private String categoryType;
    private AddButtonClickListener addButtonClickListener;



    public ServiceAndProductOrderMenuAdapter(Activity activity, ArrayList<StoreItemDetailsListCategoryInfo> storeItemDetailsList
            , HashMap<String, ArrayList<StoreItemDetailsListCategoryInfo>> finalListHashMap, String categoryType) {

        this.activity = activity;

        this.storeItemDetailsList = storeItemDetailsList;

        this.finalListHashMap = finalListHashMap;

        this.categoryType = categoryType;
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
        if(categoryType.equals("product")){
            holder.tv_name.setText(storeItemDetailsList.get(position).getProduct_name());

        }
        else if(categoryType.equals("service")){
            holder.tv_name.setText(storeItemDetailsList.get(position).getService_name());
        }


        holder.tv_add.setOnClickListener(new AddOrderClickListener(holder, storeItemDetailsList.get(position), position));

//
//        holder.tv_add.setOnClickListener {
//            holder.cl_item_minus_count_plus.visibility = View.VISIBLE
//
//        }


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

        void onAddButtonClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position);

    }

    public void setOnAddButtonClickListener(AddButtonClickListener addButtonClickListener) {

        this.addButtonClickListener = addButtonClickListener;

    }


    private class AddOrderClickListener implements View.OnClickListener {

        private ServiceAndProductOrderMenuViewHolder viewHolder;

        private StoreItemDetailsListCategoryInfo storeItemDetailsListModel;

        private int position;

        public AddOrderClickListener(ServiceAndProductOrderMenuViewHolder viewHolder, StoreItemDetailsListCategoryInfo storeItemDetailsListModel, int position) {

            this.viewHolder = viewHolder;

            this.storeItemDetailsListModel = storeItemDetailsListModel;

            this.position = position;
        }

        @Override
        public void onClick(View v) {

            addButtonClickListener.onAddButtonClickListener(viewHolder, storeItemDetailsListModel, position);

        }
    }



    public class ServiceAndProductOrderMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_business_image;
        TextView tv_price, tv_name;
        TextView tv_descp;
        TextView tv_add;
        ConstraintLayout cl_item_minus_count_plus, cl_add;
        ImageView iv_minus;
        TextView tv_item_count;
        ImageView iv_plus;

        public ServiceAndProductOrderMenuViewHolder(@NonNull View view) {
            super(view);

            iv_business_image = view.findViewById(R.id.iv_business_image);
            tv_name = view.findViewById(R.id.tv_name);
            tv_price = view.findViewById(R.id.tv_price);
            tv_descp = view.findViewById(R.id.tv_descp);
            cl_add = view.findViewById(R.id.cl_add);
            tv_add = view.findViewById(R.id.tv_add);
            cl_item_minus_count_plus = view.findViewById(R.id.cl_item_minus_count_plus);
            iv_minus = view.findViewById(R.id.iv_minus);
            tv_item_count = view.findViewById(R.id.tv_item_count);
            iv_plus = view.findViewById(R.id.iv_plus);

        }

    }



}
