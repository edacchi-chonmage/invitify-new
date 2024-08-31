package com.invitify.customer.api.customers

import com.invitify.customer.api.customers.models.Customer
import com.invitify.customer.api.customers.models.CustomerCreateRequest
import org.springframework.stereotype.Service

@Service
class CustomerService {
    fun createCustomer(request: CustomerCreateRequest): Customer {
    }
}