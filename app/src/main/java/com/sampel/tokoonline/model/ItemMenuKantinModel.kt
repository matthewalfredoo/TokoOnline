package com.sampel.tokoonline.model

import java.io.Serializable

class ItemMenuKantinModel: Serializable {
    var id: Int = 0
    lateinit var nama: String
    lateinit var deskripsi: String
    var harga: Int = 0
    var stok: Int = 0
    lateinit var gambar: String
    lateinit var noKTPAdmin: String
}