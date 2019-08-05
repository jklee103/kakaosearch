package com.example.gsontest

import com.google.gson.JsonObject

data class Response (
    var documents : ArrayList<Img>,
    var meta : JsonObject
)