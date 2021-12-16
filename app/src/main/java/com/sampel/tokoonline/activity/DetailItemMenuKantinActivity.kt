package com.sampel.tokoonline.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*
import com.google.gson.Gson
import com.sampel.tokoonline.R
import com.sampel.tokoonline.app.ApiClient
import com.sampel.tokoonline.helper.Helper
import com.sampel.tokoonline.helper.SharedPref
import com.sampel.tokoonline.model.ItemMenuKantinModel
import com.sampel.tokoonline.model.ResponModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailItemMenuKantinActivity : AppCompatActivity() {
    lateinit var s: SharedPref

    lateinit var textViewNamaDetailKantin: TextView
    lateinit var textViewHargaDetailKantin: TextView
    lateinit var imageViewDetailKantin: ImageView
    lateinit var textViewDeskripsiDetailKantin: TextView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var textViewStokDetailKantin: TextView
    lateinit var btnBeliSekarangDetailAkun: RelativeLayout

    lateinit var itemMenuKantin: ItemMenuKantinModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_menu_kantin)

        s = SharedPref(this)

        textViewNamaDetailKantin = findViewById(R.id.textViewNamaDetailKantin)
        textViewHargaDetailKantin = findViewById(R.id.textViewHargaDetailKantin)
        imageViewDetailKantin = findViewById(R.id.imageViewDetailKantin)
        textViewDeskripsiDetailKantin = findViewById(R.id.textViewDeskripsiDetailKantin)
        toolbar = findViewById(R.id.toolbar)
        textViewStokDetailKantin = findViewById(R.id.textViewStokDetailKantin)
        btnBeliSekarangDetailAkun = findViewById(R.id.btnBeliSekarangDetailAkun)

        btnBeliSekarangDetailAkun.setOnClickListener {
            sendTransaksiMenuKantin()
        }

        getInfo()
    }

    fun getInfo() {
        val dataItemMenuKantin = intent.getStringExtra("dataItemMenuKantin")
        itemMenuKantin = Gson().fromJson<ItemMenuKantinModel>(dataItemMenuKantin, ItemMenuKantinModel::class.java)

        textViewNamaDetailKantin.text = itemMenuKantin.nama
        textViewHargaDetailKantin.text = Helper().formatRupiah(itemMenuKantin.harga)
        textViewDeskripsiDetailKantin.text = itemMenuKantin.deskripsi
        textViewStokDetailKantin.text = itemMenuKantin.stok.toString()

        val gambar = Helper.BASE_URL + itemMenuKantin.gambar
        Log.d("GAMBAR", gambar)
        Picasso.get()
            .load(gambar)
            .placeholder(R.drawable.box)
            .error(R.drawable.box)
            .into(imageViewDetailKantin)

        // setting toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = itemMenuKantin.nama
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun refresh(miliseconds: Int) {
        val handler: Handler = Handler()

        val runnable: Runnable = (object : Runnable {
            override fun run() {
                getInfo()
            }
        })

        handler.postDelayed(runnable, miliseconds.toLong())
    }

    fun sendTransaksiMenuKantin() {
        ApiClient.instanceRetrofit.sendTransaksiItemMenuKantin(s.getUserModel()!!.noKTP, itemMenuKantin.id, 1).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val resp = response.body()
                if(resp?.success == 1) {
                    Toast.makeText(this@DetailItemMenuKantinActivity, resp.message, Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this@DetailItemMenuKantinActivity, resp?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@DetailItemMenuKantinActivity, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("DEBUG", t.message.toString())
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}