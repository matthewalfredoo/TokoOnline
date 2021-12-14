package com.sampel.tokoonline.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sampel.tokoonline.model.UserModel

class SharedPref(activity: Activity) {

    val login = "login"

    val noKTP = "noKTP"
    val role = "role"
    val namaLengkap = "namaLengkap"
    val noHP = "noHP"
    val email = "email"

    val user = "user"

    val myPref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean {
        return sp.getBoolean(login, false)
    }

    fun setUser(user: UserModel) {
        sp.edit().putString(noKTP, user.noKTP).apply()
        sp.edit().putString(role, user.role).apply()
        sp.edit().putString(namaLengkap, user.namaLengkap).apply()
        sp.edit().putString(noHP, user.noHP).apply()
        sp.edit().putString(email, user.email).apply()
    }

    fun getUser(): UserModel {
        val user = UserModel()

        user.noKTP = sp.getString(noKTP, null).toString()
        user.role = sp.getString(role, null).toString()
        user.namaLengkap = sp.getString(namaLengkap, null).toString()
        user.noHP = sp.getString(noHP, null).toString()
        user.email = sp.getString(email, null).toString()

        return user
    }

    fun setUserModel(userModel: UserModel) {
        val data: String = Gson().toJson(userModel, UserModel::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun getUserModel() : UserModel? {
        val data: String = sp.getString(user, null) ?: return null
        val json: UserModel = Gson().fromJson<UserModel>(data, UserModel::class.java)

        return json
    }

}