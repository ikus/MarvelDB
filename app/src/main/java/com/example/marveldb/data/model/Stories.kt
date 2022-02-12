package com.example.marveldb.data.model

import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("resourceURI") var resourceURI: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("type") var type: String?)