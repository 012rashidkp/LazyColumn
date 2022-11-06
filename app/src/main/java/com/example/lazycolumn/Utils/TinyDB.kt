package com.example.lazycolumn.Utils

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@kotlinx.serialization.Serializable
data class TinyDB(

    val details:PersistentList<Usedetails> = persistentListOf()

)

@kotlinx.serialization.Serializable
data class Usedetails(
    val email: String?,
    val username:String?,
    val password:String?,
    val city:String?,
    )
