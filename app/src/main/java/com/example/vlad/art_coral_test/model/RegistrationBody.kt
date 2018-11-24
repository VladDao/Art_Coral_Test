package com.example.vlad.art_coral_test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationBody constructor(lonigI: String, passwordI: String) {
    var username: String = lonigI
    var password: String = passwordI

}

class RegistrationResponse {
    @SerializedName("token")
    @Expose
    var token: String? = null
}