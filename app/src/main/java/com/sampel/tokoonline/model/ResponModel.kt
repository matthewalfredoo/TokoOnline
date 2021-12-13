package com.sampel.tokoonline.model

data class ResponModel(
    var success: Int = 0,
    var message: String = "",
    var user: UserModel = UserModel()
)
