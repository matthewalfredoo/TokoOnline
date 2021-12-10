package com.sampel.tokoonline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sampel.tokoonline.R
import com.sampel.tokoonline.adapter.AdapterProduk
import com.sampel.tokoonline.adapter.AdapterSlider
import com.sampel.tokoonline.model.Produk

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment() : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvProdukTerlaris: RecyclerView
    lateinit var rvElektronik: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvProdukTerlaris = view.findViewById(R.id.rv_produkTerlaris)
        rvElektronik = view.findViewById(R.id.rv_elektronik)

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider1)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(arrProduk)
        rvProduk.layoutManager = layoutManager

        rvProdukTerlaris.adapter = AdapterProduk(arrProdukTerlaris)
        rvProdukTerlaris.layoutManager = layoutManager2

        rvElektronik.adapter = AdapterProduk(arrElektronik)
        rvElektronik.layoutManager = layoutManager3

        return view
    }



    val arrProduk: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()

        val p1: Produk = Produk()
        p1.nama = "HP Keren"
        p1.harga = "Rp. 5.900.000"
        p1.gambar = R.drawable.hp

        val p2: Produk = Produk()
        p2.nama = "Laptop Gahar"
        p2.harga = "Rp. 16.800.000"
        p2.gambar = R.drawable.laptop

        val p3: Produk = Produk()
        p3.nama = "Smartwatch Cantik"
        p3.harga = "Rp. 3.850.000"
        p3.gambar = R.drawable.smartwatch

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrElektronik: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()

        val p1: Produk = Produk()
        p1.nama = "HP Keren"
        p1.harga = "Rp. 5.900.000"
        p1.gambar = R.drawable.hp

        val p2: Produk = Produk()
        p2.nama = "Laptop Gahar"
        p2.harga = "Rp. 16.800.000"
        p2.gambar = R.drawable.laptop

        val p3: Produk = Produk()
        p3.nama = "Smartwatch Cantik"
        p3.harga = "Rp. 3.850.000"
        p3.gambar = R.drawable.smartwatch

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrProdukTerlaris: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()

        val p1: Produk = Produk()
        p1.nama = "HP Keren"
        p1.harga = "Rp. 5.900.000"
        p1.gambar = R.drawable.hp

        val p2: Produk = Produk()
        p2.nama = "Laptop Gahar"
        p2.harga = "Rp. 16.800.000"
        p2.gambar = R.drawable.laptop

        val p3: Produk = Produk()
        p3.nama = "Smartwatch Cantik"
        p3.harga = "Rp. 3.850.000"
        p3.gambar = R.drawable.smartwatch

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

}