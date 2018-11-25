package com.example.vlad.art_coral_test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DinoRsponce                                                                                                                   (
    @SerializedName("dinos")
    var dinos: List<Dino>? = null)

data class Dino (
    @SerializedName("dino")
    var dino: DinoDetails? = null
)

data class DinoImage (
    @SerializedName("src")
    var src: String? = null,
    @SerializedName("alt")
    var alt: String? = null
)

data class DinoDetails (
    @SerializedName("dino_title")
    var dinoTitle: String? = null,
    @SerializedName("dino_color")
    var dinoColor: String? = null,
    @SerializedName("dino_birthdate")
    var dinoBirthdate: String? = null,
    @SerializedName("dino_image")
    var dinoImage: DinoImage? = null,
    @SerializedName("dino_about")
    var dinoAbout: String? = null)


