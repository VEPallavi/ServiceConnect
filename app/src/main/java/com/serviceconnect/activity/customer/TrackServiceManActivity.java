package com.serviceconnect.activity.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.serviceconnect.R;

public class TrackServiceManActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivBack;
    TextView tv_title;
    TextView txt_label_tip_amount;
    TextView payment_method, tv_payment_method, date, tv_date, tv_status;
    View divider_3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_track_service_man);

        initViews();
        setOnClickListener();
    }



    private void initViews() {
        ivBack = findViewById(R.id.ivBack);
        tv_title = findViewById(R.id.tv_title);
        txt_label_tip_amount = findViewById(R.id.txt_label_tip_amount);
        payment_method = findViewById(R.id.payment_method);
        tv_payment_method = findViewById(R.id.tv_payment_method);
        divider_3 = findViewById(R.id.divider_3);
        date = findViewById(R.id.date);
        tv_date = findViewById(R.id.tv_date);
        tv_status = findViewById(R.id.tv_status);
        txt_label_tip_amount.setText("Tip After Service");

        tv_title.setText("Track Service Man");
        payment_method.setVisibility(View.GONE);
        tv_payment_method.setVisibility(View.GONE);
        divider_3.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        tv_date.setVisibility(View.GONE);
        tv_status.setVisibility(View.GONE);


    }

    private void setOnClickListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivBack :
                finish();
                break;
        }
    }
}
