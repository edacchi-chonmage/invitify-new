package com.invitify.customer.api.customers.presentation

import com.invitify.customer.api.customers.infrastructure.CustomerRepository
import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerEditRequest
import com.invitify.customer.api.customers.model.CustomerRegisterRequest
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {
    fun search(): List<Customer> {
        return customerRepository.search()
    }

    fun findById(id: Int): Customer {
        return customerRepository.findById(id)
    }

    fun register(request: CustomerRegisterRequest): Customer {
        return customerRepository.register(request)
    }

    fun edit(id: Int, request: CustomerEditRequest): Customer {
        return customerRepository.update(id, request)
    }
}