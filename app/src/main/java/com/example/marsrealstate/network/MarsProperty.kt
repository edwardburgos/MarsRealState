package com.example.marsrealstate.network

import com.squareup.moshi.Json

// If our properties names are the same with the ones from JSON
// data class MarsProperty (val price: Double, val id: String, val type: String, val img_src: String)

// If our properties names are different from the ones from JSON
data class MarsProperty (val price: Double, val id: String, val type: String, @Json(name = "img_src") val imgUrl: String)