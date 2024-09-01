package com.invitify.customer.api.customers.presentation

import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerEditRequest
import com.invitify.customer.api.customers.model.CustomerRegisterRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @GetMapping
    fun search(): ResponseEntity<List<Customer>> {
        return ResponseEntity(
            customerService.search(),
            HttpStatus.OK
        )
    }

    @GetMapping("{id}")
    fun findById(
        @PathVariable id: Int
    ): ResponseEntity<Customer> = ResponseEntity(
        customerService.findById(id),
        HttpStatus.OK
    )

    @PostMapping
    fun register(
        @Validated request: CustomerRegisterRequest
    ): ResponseEntity<Customer> = ResponseEntity(
        customerService.register(request),
        HttpStatus.CREATED
    )

    @PutMapping("{id}")
    fun edit(
        @PathVariable id: Int,
        @Validated request: CustomerEditRequest
    ): ResponseEntity<Customer> = ResponseEntity(
        customerService.edit(id, request),
        HttpStatus.OK
    )
}