package com.invitify.customer.api.customers.model

data class CustomerCreateRequest(
    val name: String,
    val email: String,
)