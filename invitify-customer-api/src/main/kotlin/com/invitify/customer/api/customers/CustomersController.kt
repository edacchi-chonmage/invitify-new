package com.invitify.customer.api.customers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomersController {
    @PostMapping
    fun createCustomer() {
        HttpStatus.OK
    }
}