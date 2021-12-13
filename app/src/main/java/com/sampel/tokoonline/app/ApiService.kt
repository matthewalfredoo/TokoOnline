package com.sampel.tokoonline.app

import com.sampel.tokoonline.model.ResponModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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

}