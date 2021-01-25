package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.HomeActivityCustomer
import com.Servicehubconnect.activity.servicePerson.HomeActivitySP
import com.Servicehubconnect.helper.AppPreference
import com.Servicehubconnect.helper.Utils

class SplashActivity : AppCompatActivity(){
    private val SPLASH_TIME_OUT: Long = 500
    var preference: AppPreference?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        preference = AppPreference.getInstance(this)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        Handler().postDelayed(Runnable {

            if (preference!!.getAuthToken().equals("")) {

                if(preference!!.getAppType().equals("")){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else {
                if (!Utils.isInternetAvailable(this)) {
                    Utils.showToast(this, resources.getString(R.string.msg_no_internet))
                }
                else {

                    if(preference!!.getAppType().equals("1")){
                        var intent = Intent(this, HomeActivityCustomer::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else if(preference!!.getAppType().equals("2")){
                        var intent = Intent(this, HomeActivitySP::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }, SPLASH_TIME_OUT)


//        val t = object:Thread(){
//            override fun run() {
//                super.run()
//                sleep(2000)
//                val intent =Intent(this@SplashActivity, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        t.start()


    }

}