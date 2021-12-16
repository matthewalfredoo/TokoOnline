package com.sampel.tokoonline.model

class ItemMenuKantinResponModel(
    success: Int,
    message: String,
    var itemMenuKantin: ArrayList<ItemMenuKantinModel> = ArrayList()
): ResponModel(success, message, UserModel())