package com.Servicehubconnect.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Servicehubconnect.R;
import com.Servicehubconnect.helper.AppConstants;


/**
 * Created by mobi269 on 9/6/16.
 */
public class TwoButtonActionDialog extends AlertDialog {

    private Typeface typeFaceMyriad;
    private TextView txtNotificationMsg;
    private Button btnOkay;
    private Activity activity;
    private Button btnCancel;
    private String message;
    private String okayMsg;
    private String cancelMsg;
    private View.OnClickListener okButtonListener;
    private View.OnClickListener cancelButtonListener;

    public TwoButtonActionDialog(Activity activity, String okayMsg, String cancelMsg, String message,
                                 View.OnClickListener okButtonListener, View.OnClickListener cancelButtonListener) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.okayMsg = okayMsg;
        this.cancelMsg = cancelMsg;
        typeFaceMyriad = Typeface.create(AppConstants.FONT_TYPE_VARELA_ROUND, Typeface.NORMAL);
        this.okButtonListener = okButtonListener;
        this.cancelButtonListener = cancelButtonListener;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iniUI();

    }

    private void iniUI() {

        txtNotificationMsg = (TextView) findViewById(R.id.tv_common_msg);
        btnCancel = (Button) findViewById(R.id.tv_no);
        btnOkay = (Button) findViewById(R.id.tv_yes);

        btnOkay.setTypeface(typeFaceMyriad);
        btnCancel.setTypeface(typeFaceMyriad);
        txtNotificationMsg.setTypeface(typeFaceMyriad);

        btnOkay.setOnClickListener(okButtonListener);
        btnCancel.setOnClickListener(cancelButtonListener);

        txtNotificationMsg.setText(message);
        btnOkay.setText(okayMsg);
        btnCancel.setText(cancelMsg);

    }
}
