package com.example.marveldb.data.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("resourceURI") var resourceURI: String?,
    @SerializedName("name") var name: String?)