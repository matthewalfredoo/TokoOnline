package com.sampel.tokoonline.app

import com.sampel.tokoonline.model.ItemMenuKantinModel
import com.sampel.tokoonline.model.ItemMenuKantinResponModel
import com.sampel.tokoonline.model.ResponModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("noKTP") noKTP: String,
        @Field("namaLengkap") namaLengkap: String,
        @Field("noHP") noHP: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponModel>

    /* Mengambil data item menu kantin */
    @GET("item-menu-kantin/all")
    fun getProduk(): Call<ItemMenuKantinResponModel>


    /* Mengirimkan data transaksi item menu kantin */
    @FormUrlEncoded
    @POST("transaksi-menu-kantin/store")
    fun sendTransaksiItemMenuKantin(
        @Field("noKTP_customer") noKTP_customer: String,
        @Field("id_menu_kantin") id_menu_kantin: Int,
        @Field("jumlah") jumlah: Int
    ): Call<ResponModel>

}