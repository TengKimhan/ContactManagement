package com.soramitsukhmer.contactmanagement.api.exception

data class FieldNotFoundException(val value:String, val id: String) : RuntimeException("$value [$id]") {
}