package com.serviceconnect.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class SignUpActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        iv_back.setOnClickListener {
            finish()
        }


    }

}