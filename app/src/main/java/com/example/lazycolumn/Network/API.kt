package com.example.lazycolumn.Network

class API {
    companion object{
        val BaseUrl="http://192.168.102.131:8000/api/"
        const val Login="login/"
        val Registerusers=BaseUrl+"register/"
        const val Upload="fileupload/"
        const val retrievefile="getfiles/"
        const val deletefile="deletefile/"
    }
}