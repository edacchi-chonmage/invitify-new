package com.invitify.customer.api.customers.presentation

import com.invitify.customer.api.customers.infrastructure.CustomerRepository
import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerCreateRequest
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {
    fun createCustomer(request: CustomerCreateRequest): Customer {
        return customerRepository.createCustomer(request)
    }
}