package com.example.software_engineer.ui

abstract class BaseResp (status_msg:String,status_code:Int){
    open val status_code=status_code
    open val status_msg=status_msg
}