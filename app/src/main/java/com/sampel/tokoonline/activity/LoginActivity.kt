package com.sampel.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sampel.tokoonline.R
import com.sampel.tokoonline.helper.SharedPref

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    private lateinit var btnProsesLogin: Button
    private lateinit var btnProsesRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)


    }

    private fun mainButton() {
        btnProsesLogin.setOnClickListener {
            s.setStatusLogin(true)
        }

        btnProsesRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}