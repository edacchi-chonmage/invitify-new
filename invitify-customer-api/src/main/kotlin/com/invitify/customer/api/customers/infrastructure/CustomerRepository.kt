package com.invitify.customer.api.customers.infrastructure

import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerCreateRequest
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    val dsl: DSLContext
) {
    fun createCustomer(request: CustomerCreateRequest) {
    }

    private fun upsertCustomer(customer: Customer) {
    }
}