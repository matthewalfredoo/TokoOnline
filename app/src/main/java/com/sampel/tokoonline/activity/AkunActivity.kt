package com.sampel.tokoonline.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sampel.tokoonline.R
import com.sampel.tokoonline.helper.SharedPref

class AkunActivity : AppCompatActivity() {

    private lateinit var btn_prosesLogin: Button
    private lateinit var btn_prosesRegister: Button

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        btn_prosesLogin = findViewById(R.id.btn_prosesLogin)

        s = SharedPref(this)

        btn_prosesLogin.setOnClickListener {
            s.setStatusLogin(true)
        }
    }
}