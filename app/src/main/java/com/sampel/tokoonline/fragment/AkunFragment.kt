package com.sampel.tokoonline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.sampel.tokoonline.R
import com.sampel.tokoonline.helper.SharedPref

class AkunFragment : Fragment() {

    lateinit var s: SharedPref
    lateinit var btnLogout: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        btnLogout = view.findViewById(R.id.btn_logout)

        s = SharedPref(this.requireActivity())

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
        }

        return view
    }

}