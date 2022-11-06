package com.example.lazycolumn.Model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Products(
    var id: String? = null,
    var name: String? = null,
    var price: Int? = null,
    @ServerTimestamp
    var date: Date? = null
)
