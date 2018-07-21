package com.padcmyanmar.myapplication.events

class ErrorEvent
{
    class ApiErrorEvent(private val throwable: Throwable? =null)
    {
            fun getMessage(): String? {
                return if (throwable == null)
                    "Null throwable Error"
                else
                    throwable.message
            }
    }
}