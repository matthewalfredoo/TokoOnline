package com.sampel.tokoonline.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sampel.tokoonline.R
import com.sampel.tokoonline.activity.DetailItemMenuKantinActivity
import com.sampel.tokoonline.activity.LoginActivity
import com.sampel.tokoonline.helper.Helper
import com.sampel.tokoonline.model.ItemMenuKantinModel
import com.sampel.tokoonline.model.Produk
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterProduk(var activity: Activity, var data: ArrayList<ItemMenuKantinModel>): RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvNama:TextView = view.findViewById(R.id.tv_nama)
        val tvHarga:TextView = view.findViewById(R.id.tv_harga)
        val imgProduk:ImageView = view.findViewById(R.id.img_produk)
        val cardViewLayoutItemProduk: CardView = view.findViewById(R.id.cardViewLayoutItemProduk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].nama
        holder.tvHarga.text = Helper().formatRupiah(data[position].harga)
//        holder.imgProduk.setImageResource(R.drawable.box)
        val image = Helper.BASE_URL + data[position].gambar
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.box)
            .error(R.drawable.box)
            .into(holder.imgProduk)

        holder.cardViewLayoutItemProduk.setOnClickListener {
            val intent = Intent(activity, DetailItemMenuKantinActivity::class.java)
            val dataItemMenuKantin = Gson().toJson(data[position], ItemMenuKantinModel::class.java)
            intent.putExtra("dataItemMenuKantin", dataItemMenuKantin)

            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}