package com.sampel.tokoonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampel.tokoonline.R
import com.sampel.tokoonline.model.Produk

class AdapterProduk(var data: ArrayList<Produk>): RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvNama:TextView = view.findViewById(R.id.tv_nama)
        val tvHarga:TextView = view.findViewById(R.id.tv_harga)
        val imgProduk:ImageView = view.findViewById(R.id.img_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].nama
        holder.tvHarga.text = data[position].harga
        holder.imgProduk.setImageResource(data[position].gambar)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}