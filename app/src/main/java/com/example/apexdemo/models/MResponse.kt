package com.example.apexdemo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//respuesta del request
class MResponse {
    @SerializedName("results") @Expose
    var results: List<MCharacter> = listOf();
}