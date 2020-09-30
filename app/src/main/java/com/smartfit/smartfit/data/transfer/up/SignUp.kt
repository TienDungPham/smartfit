package com.smartfit.smartfit.data.transfer.up

data class SignUp(
    var name: String,
    var weight: Int,
    var height: Int,
    var age: Int,
    var gender: Boolean,
    var goal: String,
    var phoneIdentity: String,
    var phoneNumber: String
)