package com.example.vlad.art_coral_test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationBody constructor(lonigI: String, passwordI: String) {
    var username: String = lonigI
    var password: String = passwordI

}

class Authorization constructor(token:String,session_name:String,session_id:String){
    var X_CSRF_Token:String = "X-CSRF-Token: $token"
    var Cookie:String = "Cookie: $session_name=$session_id"
//  var X_CSRF_Token:String = "X-CSRF-Token: $token"
  //  var Cookie:String = "Cookie: $session_name=$session_id"

}
/*
const val X_CSRF_Token:String = "X-CSRF-Token"
        const val Cookie :String = "Cookie"
        */
class RegistrationResponse {
    @SerializedName("token")

    var token: String? = null
}