package com.example.vlad.art_coral_test

import com.example.vlad.art_coral_test.MainViewModel.Auth.HeadAuth
import com.example.vlad.art_coral_test.MainViewModel.Auth.X_CSRF_Token
import com.example.vlad.art_coral_test.MainViewModel.Auth.Cookie
import com.example.vlad.art_coral_test.model.*
import io.reactivex.Single
import okhttp3.Cookie
import retrofit2.http.*

interface ApiService {
    @Headers(
        "Content-Type:application/json",
        "Accept:application/json")
    @POST("/rest/user/login")
    fun loginWithCredentials(@Body registrationBody: RegistrationBody): Single<AuthResponse>


   // @Header (MainViewModel.X_CSRF_Token)token:String,
    //@Header (MainViewModel.Cookie)cookie:String
   @Headers(
       "Content-Type:application/json",
       "Accept:application/json")
    @GET("/rest/dinos")
    fun getDinos(@Header (X_CSRF_Token)token:String,
                 @Header(Cookie)Cook:String
                 ):Single <DinoRsponce>
//@Header (X_CSRF_Token)token:String,
//                 @Header(Cookie)Cook:String



}