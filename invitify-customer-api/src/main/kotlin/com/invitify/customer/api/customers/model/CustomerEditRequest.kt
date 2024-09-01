package com.invitify.customer.api.customers.model

data class CustomerEditRequest(
    val id: Int,
    val name: String,
    val email: String,
)
