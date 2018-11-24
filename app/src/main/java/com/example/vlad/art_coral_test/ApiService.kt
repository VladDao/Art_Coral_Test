package com.example.vlad.art_coral_test

import com.example.vlad.art_coral_test.model.AuthResponse
import com.example.vlad.art_coral_test.model.RegistrationBody
import io.reactivex.Single

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/rest/user/login")
    fun loginWithCredentials(@Body registrationBody: RegistrationBody): Single<AuthResponse>



}