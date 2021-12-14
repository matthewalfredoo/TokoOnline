package com.sampel.tokoonline.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.sampel.tokoonline.MainActivity
import com.sampel.tokoonline.R
import com.sampel.tokoonline.activity.AkunActivity
import com.sampel.tokoonline.helper.SharedPref

class AkunFragment : Fragment() {

    lateinit var s: SharedPref
    lateinit var btnLogout: TextView

    lateinit var textViewNamaAkun: TextView
    lateinit var textViewNoKTPAkun: TextView
    lateinit var textViewEmailAkun: TextView
    lateinit var textViewNoHPAkun: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        init(view)

        s = SharedPref(this.requireActivity())

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
            val intent = Intent(this.requireActivity(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setData()

        return view
    }

    fun setData() {
        val user = s.getUser()
        textViewNamaAkun.text = user.namaLengkap
        textViewNoKTPAkun.text = user.noKTP
        textViewEmailAkun.text = user.email
        textViewNoHPAkun.text = user.noHP
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_logout)
        textViewNamaAkun = view.findViewById(R.id.textViewNamaAkun)
        textViewNoKTPAkun = view.findViewById(R.id.textViewNoKTPAkun)
        textViewEmailAkun = view.findViewById(R.id.textViewEmailAkun)
        textViewNoHPAkun = view.findViewById(R.id.textViewNoHPAkun)
    }

}