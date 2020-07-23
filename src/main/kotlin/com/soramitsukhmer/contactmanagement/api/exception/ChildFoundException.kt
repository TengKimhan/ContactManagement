package com.soramitsukhmer.contactmanagement.api.exception

data class ChildFoundException(val msg:String):RuntimeException(msg) {
}