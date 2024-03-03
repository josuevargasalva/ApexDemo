package com.example.apexdemo.common

//posibles estados de un request
enum class State(var error: String = "", var code: Int = 200) {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}