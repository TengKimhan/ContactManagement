package com.soramitsukhmer.contactmanagement.api.exception

data class IDNotFoundException(val msg: String) : RuntimeException(msg) {
}