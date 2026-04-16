package com.gift.chui_sokogarden

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payments : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Receive extra data
//        data is passed via an intent

        val productname=intent.getStringExtra("product_name")
        val productcost=intent.getIntExtra("product_cost",0)
        val productphoto=intent.getStringExtra("product_photo")
        val productdescription=intent.getStringExtra("product_description")






//        finding the views by id
       val image=findViewById<ImageView>(R.id.product_photo)
        val txtName=findViewById<TextView>(R.id.txtxProductName)
        val txtCost=findViewById<TextView>(R.id.txtProductCost)
        val txtphone=findViewById<TextView>(R.id.phone)
        val btnPay=findViewById<Button>(R.id.pay)
        val product_description=findViewById<TextView>(R.id.product_description)



//        Update TextViews with Values Passed via Intent
        txtName.text=productname
        txtCost.text="Ksh $productcost"
        product_description.text=productdescription

        Glide.with(this)
            .load(productphoto)
            .circleCrop()
            .into(image)


        btnPay.setOnClickListener {
            val api="https://gift.alwaysdata.net/api/mpesa_payment"
//            Create data using RequestParams, put phone and cost as keyvalue pairs
            val data = RequestParams()
            data.put("amount",productcost)  //passed via intent
            data.put("phone",txtphone.text.toString())  //entered by user in phone edittext

//            access api helper
            val helper= ApiHelper(applicationContext)
//            post data to api endpoint
            helper.post(api,data)






        }

    }
}