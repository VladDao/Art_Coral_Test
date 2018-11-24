package com.example.vlad.art_coral_test.model

import com.google.gson.annotations.SerializedName
data class AuthResponse (

    @SerializedName("sessid")
    var sessid: String,
    @SerializedName("session_name")
    var sessionName: String,
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var user: User)



data class User (
    @SerializedName("uid")
    var uid: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("mail")
    var mail: String,
    @SerializedName("theme")
    var theme: String,
    @SerializedName("signature")
    var signature: String,
    @SerializedName("signature_format")
    var signatureFormat: String,
    @SerializedName("created")
    var created: String,
    @SerializedName("access")
    var access: String,
    @SerializedName("login")
    var login: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("timezone")
    var timezone: Any,
    @SerializedName("language")
    var language: String,
    @SerializedName("picture")
    var picture: String,
    @SerializedName("data")
    var data: Boolean,
    @SerializedName("uuid")
    var uuid: String,
    @SerializedName("roles")
    var roles: Roles,
    @SerializedName("rdf_mapping")
    var rdfMapping: RdfMapping)

data class Roles(
    @SerializedName("2")
    var _2: String)



data class RdfMapping (
    @SerializedName("rdftype")
    var rdftype: List<String>,
    @SerializedName("name")
    var name: Name,
    @SerializedName("homepage")
    var homepage: Homepage)

data class Name (
    @SerializedName("predicates")
    var predicates: List<String>)

data class Homepage(
    @SerializedName("predicates")
    var predicates: List<String>,
    @SerializedName("type")
    var type: String)