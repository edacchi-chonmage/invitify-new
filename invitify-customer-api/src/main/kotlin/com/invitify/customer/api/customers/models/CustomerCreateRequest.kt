package com.invitify.customer.api.customers.models

data class CustomerCreateRequest(
    val name: String,
    val email: String,
)