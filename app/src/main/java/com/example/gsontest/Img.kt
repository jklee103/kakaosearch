package com.example.gsontest

data class Img(
    var collection: String,
    var thumbnail_url: String,
    var image_url: String,
    var width: String,
    var height: String,
    var display_sitename: String,
    var doc_url: String,
    var datetime: String
)

fun getProgressItem(): Img {
    return Img(
        "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
    )
}