package com.sampel.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sampel.tokoonline.R
import com.sampel.tokoonline.helper.SharedPref

class AkunActivity : AppCompatActivity() {

    private lateinit var btnProsesLogin: Button
    private lateinit var btnProsesRegister: Button

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        btnProsesLogin = findViewById(R.id.btn_prosesLogin)
        btnProsesRegister = findViewById(R.id.btn_prosesRegister)

        s = SharedPref(this)

        mainButton()
    }

    private fun mainButton() {
        btnProsesLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnProsesRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}