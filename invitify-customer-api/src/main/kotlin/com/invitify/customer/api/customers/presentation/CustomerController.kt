package com.invitify.customer.api.customers.presentation

import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerCreateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping("")
    fun createCustomer(
        @Validated request: CustomerCreateRequest
    ): ResponseEntity<Customer> = ResponseEntity(
        customerService.createCustomer(request),
        HttpStatus.CREATED
    )
}