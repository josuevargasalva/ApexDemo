package com.example.apexdemo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//modelo de personajes
class MCharacter {

    @SerializedName("name")
    var name: String = "";

    @SerializedName("image")
    var image: String = "";

    @SerializedName("status")
    var status: String = "";

    @SerializedName("species")
    var species: String = "";

}