package com.CodeNaroNa.vendor.relief.GlobalHelpers

//State management Wrapper class
sealed class Resource<T>(
        val data :T? = null,
        val message :String? = null
)
{
    class Success<T>(data: T,message: String?=null) : Resource<T>(data,message)
    class Error<T>(data: T?=null,message: String) :Resource<T>(data,message)
    class Loading<T> :Resource<T>()
    class NavigateForward<T>(data: T?= null):Resource<T>(data)
}