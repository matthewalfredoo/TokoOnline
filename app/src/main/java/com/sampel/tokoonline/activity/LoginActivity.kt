package com.sampel.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.sampel.tokoonline.MainActivity
import com.sampel.tokoonline.R
import com.sampel.tokoonline.app.ApiClient
import com.sampel.tokoonline.helper.SharedPref
import com.sampel.tokoonline.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    private lateinit var btnProsesLogin: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBarLogin: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        btnProsesLogin = findViewById(R.id.btnLoginLoginActivity)
        editTextEmail = findViewById(R.id.editTextEmailLogin)
        editTextPassword = findViewById(R.id.editTextPasswordLogin)
        progressBarLogin = findViewById(R.id.progressBarLogin)

        btnProsesLogin.setOnClickListener {
            login()
        }

    }

    fun login () {
        val inputEmail = editTextEmail.text
        val inputPassword = editTextPassword.text

        if(inputEmail.isEmpty()) {
            error(editTextEmail)
        }
        if(inputPassword.isEmpty()) {
            error(editTextPassword)
        }

        progressBarLogin.visibility = View.VISIBLE

        ApiClient.instanceRetrofit
            .login(inputEmail.toString(), inputPassword.toString())
            .enqueue(object : Callback<ResponModel>{
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    progressBarLogin.visibility = View.GONE

                    val resp = response.body()!!

                    if(resp.success == 1) {
                        Toast.makeText(this@LoginActivity, "Selamat datang " + resp.user.namaLengkap, Toast.LENGTH_SHORT).show()
                        s.setStatusLogin(true)
                        s.setUser(resp.user)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Error: " + resp.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    progressBarLogin.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun error(editText: EditText) {
        editText.error = "Kolom ini tidak boleh kosong"
    }

}