package com.sampel.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.sampel.tokoonline.R
import com.sampel.tokoonline.app.ApiClient
import com.sampel.tokoonline.helper.SharedPref
import com.sampel.tokoonline.model.ResponModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    lateinit var s: SharedPref

    private lateinit var editTextNoKTPRegister: EditText
    private lateinit var editTextNamaLengkapRegister: EditText
    private lateinit var editTextNoHPRegister: EditText
    private lateinit var editTextEmailRegister: EditText
    private lateinit var editTextPasswordRegister: EditText
    private lateinit var btnRegisterRegister: Button
    private lateinit var progressBarRegister: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s = SharedPref(this)

        editTextNoKTPRegister = findViewById(R.id.editTextNoKTPRegister)
        editTextNamaLengkapRegister = findViewById(R.id.editTextNamaLengkapRegister)
        editTextNoHPRegister = findViewById(R.id.editTextNoHPRegister)
        editTextEmailRegister = findViewById(R.id.editTextEmailRegister)
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister)
        btnRegisterRegister = findViewById(R.id.btn_registerRegisterActivity)
        progressBarRegister = findViewById(R.id.progressBarRegister)

        btnRegisterRegister.setOnClickListener {
            register()
        }
    }

    fun register(){
        val inputNoKTP = editTextNoKTPRegister.text.toString()
        val inputNamaLengkap = editTextNamaLengkapRegister.text.toString()
        val inputNoHP = editTextNoHPRegister.text.toString()
        val inputEmail = editTextEmailRegister.text.toString()
        val inputPassword = editTextPasswordRegister.text.toString()

        if(editTextNoKTPRegister.text.isEmpty()) {
            error(editTextNoKTPRegister)
        }
        if(editTextNamaLengkapRegister.text.isEmpty()) {
            error(editTextNamaLengkapRegister)
        }
        if(editTextNoHPRegister.text.isEmpty()){
            error(editTextNoHPRegister)
        }
        if(editTextEmailRegister.text.isEmpty()) {
            error(editTextEmailRegister)
        }
        if(editTextPasswordRegister.text.isEmpty()) {
            error(editTextPasswordRegister)
        }

        progressBarRegister.visibility = View.VISIBLE
        ApiClient
            .instanceRetrofit
            .register(inputNoKTP, inputNamaLengkap, inputNoHP, inputEmail, inputPassword)
            .enqueue(object: Callback<ResponModel>{
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    val resp = response.body()!!
                    progressBarRegister.visibility = View.GONE

                    if(resp.success == 1) {
                        Toast.makeText(this@RegisterActivity, "Silahkan login " + resp.user.namaLengkap, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this@RegisterActivity, "Error: " + resp.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
                    progressBarRegister.visibility = View.GONE
                }

            })
    }

    fun error(editText: EditText) {
        editText.error = "Kolom ini tidak boleh kosong"
    }
}