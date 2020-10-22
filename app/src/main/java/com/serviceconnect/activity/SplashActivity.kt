package com.serviceconnect.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R

class SplashActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val t = object:Thread(){
            override fun run() {
                super.run()
                sleep(2000)
                val intent =Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        t.start()


    }

}