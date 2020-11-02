package com.serviceconnect.activity.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.serviceconnect.R;

public class SetLocationActivity extends FragmentActivity implements View.OnClickListener {
    ImageView iv_back;
    TextView tv_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_set_location_activity);

        initViews();
    }

    private void initViews() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText("Set Location");

        iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
