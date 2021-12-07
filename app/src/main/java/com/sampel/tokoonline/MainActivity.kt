package com.sampel.tokoonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sampel.tokoonline.fragment.AkunFragment
import com.sampel.tokoonline.fragment.HomeFragment
import com.sampel.tokoonline.fragment.KeranjangFragment

class MainActivity : AppCompatActivity() {
    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentAkun: Fragment = AkunFragment()
    private val fragmentKeranjang: Fragment = KeranjangFragment()
    private val fragmentManage: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var  menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
    }

    fun setupBottomNav() {
        fragmentManage.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fragmentManage.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()
        fragmentManage.beginTransaction().add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_keranjang -> {
                    callFragment(1, fragmentKeranjang)
                }
                R.id.navigation_akun -> {
                    callFragment(2, fragmentAkun)
                }
            }

            false

        }
    }

    fun callFragment(index: Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fragmentManage.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}