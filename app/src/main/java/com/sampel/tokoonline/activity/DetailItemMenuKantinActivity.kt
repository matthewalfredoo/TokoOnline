package com.sampel.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
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

class DetailItemMenuKantinActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {
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


        if(s.getStatusLogin() == true) {
            btnBeliSekarangDetailAkun.setOnClickListener {
                sendTransaksiMenuKantin()
            }
        }
        else {
            btnBeliSekarangDetailAkun.setOnClickListener {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
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
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Jumlah " + itemMenuKantin.nama + " yang akan dibeli")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_jumlah_beli, null)
        val numberPickerStockBeliItem = dialogLayout.findViewById<NumberPicker>(R.id.numberPickerStockBeliItem)
//        val buttonOrder = dialogLayout.findViewById<Button>(R.id.buttonOrder)
//        val buttonCancel = dialogLayout.findViewById<Button>(R.id.buttonCancel)

        numberPickerStockBeliItem.maxValue = itemMenuKantin.stok
        numberPickerStockBeliItem.minValue = 1
        numberPickerStockBeliItem.wrapSelectorWheel = false
        numberPickerStockBeliItem.setOnValueChangedListener(this)

//        buttonOrder.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                processSendTransaksiMenuKantin(numberPickerStockBeliItem.value)
//            }
//        })

        builder.setView(dialogLayout)
        builder.setPositiveButton("Pesan") { dialogIneterface, i ->
            val pickedValue: Int = numberPickerStockBeliItem.value
            Log.d("DEBUG_VALUE_NUMPICKER", pickedValue.toString())
            processSendTransaksiMenuKantin(pickedValue)
        }
        builder.setNegativeButton("Batal") { dialogInterface, i ->
            Toast.makeText(this, "Batal", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun processSendTransaksiMenuKantin(jumlah: Int){
        ApiClient.instanceRetrofit.sendTransaksiItemMenuKantin(s.getUserModel()!!.noKTP, itemMenuKantin.id, jumlah).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val resp = response.body()
                Log.d("DEBUG_RESPON_DETAIL", resp?.message.toString())
                if(resp?.success == 1) {
                    Toast.makeText(this@DetailItemMenuKantinActivity, resp.message, Toast.LENGTH_LONG).show()
                }
                else {
//                    Toast.makeText(this@DetailItemMenuKantinActivity, resp?.message, Toast.LENGTH_LONG).show()
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

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        Log.d("value is ", newVal.toString())
    }
}