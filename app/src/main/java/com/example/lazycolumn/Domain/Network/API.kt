package com.example.lazycolumn.Domain.Network

 class API {
    companion object{
        val BaseUrl="http://192.168.166.131:8080/auth/"
        const val Login="login/"
        val Registerusers= BaseUrl +"registeruser"
        const val Upload="fileupload/"
        const val retrievefile="getfiles/"
        const val deletefile="deletefile/"
    }
}