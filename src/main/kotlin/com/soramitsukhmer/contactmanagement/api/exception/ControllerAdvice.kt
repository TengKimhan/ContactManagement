package com.soramitsukhmer.contactmanagement.api.exception

import com.soramitsukhmer.contactmanagement.api.response.ErrorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerAdvice {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(FieldNotFoundException::class)
    fun iDNotFoundException(request: HttpServletRequest, e: FieldNotFoundException?) : ResponseEntity<ErrorResponse>{
        log.error("IDNotFoundException: $e")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(errorMessage = e?.message + " Is Not found"))
    }

    @ExceptionHandler(ChildFoundException::class)
    fun childFoundException(request: HttpServletRequest, e: ChildFoundException) : ResponseEntity<ErrorResponse>{
        log.error("ChildFoundException: $e")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(errorMessage = e?.msg))
    }

    @ExceptionHandler(Exception::class)
    fun generalErrorException(request: HttpServletRequest, e: Exception?) : ResponseEntity<ErrorResponse>{
        log.error("Exception: $e")
        val errorResponse = ErrorResponse(errorMessage = e?.message)
        val cause = e?.cause?.message
        cause?.let {
            when{
                cause.contains("could not execute statement") -> {
                    errorResponse.errorMessage = "ERROR RUNNING SQL"
                }
            }
        }
        return ResponseEntity.ok(errorResponse)
    }

    @ExceptionHandler(RecordIsAlreadyExistException::class)
    fun recordIsAlreadyExistException(request: HttpServletRequest, e: RecordIsAlreadyExistException?) : ResponseEntity<ErrorResponse>{
        log.error("RecordIsAlreadyExistException: $e")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(errorMessage = e?.msg))
    }


}