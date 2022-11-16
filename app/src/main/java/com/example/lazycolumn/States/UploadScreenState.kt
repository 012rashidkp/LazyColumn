package com.example.lazycolumn.States

import android.net.Uri

data class UploadScreenState(
    val listOfSelectedImages:List<Uri> = emptyList()
)
