package com.example.myapplication.login

data class SignIn (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)