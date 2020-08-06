package com.example.signinappsample.model

 class User (
     val avatar: String,
     val email: String,
     val id: String,
     val name: String
 )

//if name of parameters different from that of server use serialisedname like below

/*
data class User(
    @SerializedName("avatar")
    val image: String,
    @SerializedName("email")
    val userEmail: String,
    @SerializedName("id")
    val userId: String,
    @SerializedName("name")
    val userName: String
)
*/

