package com.example.apexdemo.api

import com.example.apexdemo.models.MResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    //Request get de todos los personajes
    @GET("api/character")
    fun getCharacters(
    ): Observable<MResponse>

}