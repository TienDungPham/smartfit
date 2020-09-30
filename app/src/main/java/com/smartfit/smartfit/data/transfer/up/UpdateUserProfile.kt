package com.smartfit.smartfit.data.transfer.up

data class UpdateUserProfile(
    var name: String,
    var imageUrl: String,
    var weight: Int,
    var height: Int,
    var age: Int,
    var gender: Boolean,
    var goal: String
)