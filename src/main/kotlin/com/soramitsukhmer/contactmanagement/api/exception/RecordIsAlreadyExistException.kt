package com.soramitsukhmer.contactmanagement.api.exception

import java.lang.RuntimeException

data class RecordIsAlreadyExistException(val msg:String): RuntimeException(msg) {
}