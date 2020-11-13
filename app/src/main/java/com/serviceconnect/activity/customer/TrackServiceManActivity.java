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

        txt_label_tip_amount.setText("Tip After Service");

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
