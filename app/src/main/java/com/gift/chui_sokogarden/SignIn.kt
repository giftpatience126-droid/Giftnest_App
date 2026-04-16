package com.gift.chui_sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //        sign up link activity
        val signinlink = findViewById<TextView>(R.id.signin_link)

//        set onClick listener

        signinlink.setOnClickListener {
            val signinlinkIntent = Intent(applicationContext, SignUp::class.java)
            startActivity(signinlinkIntent)
        }

//           https://gift.alwaysdata.net/api/signin
//           https://gift.alwaysdata.net/api/signup
//           https://gift.alwaysdata.net/api/getproductdetails
//           https://gift.alwaysdata.net/api/mpesa_payment



//        find the edit text buttons by id
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val signinbutton=findViewById<Button>(R.id.signin)
        signinbutton.setOnClickListener {
            val api = "https://gift.alwaysdata.net/api/signin"


            val data = RequestParams()

            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

//        api helper-it delivers our data to the api

            val helper = ApiHelper(applicationContext)
            helper.post_login(api, data)

        }
    }
}
