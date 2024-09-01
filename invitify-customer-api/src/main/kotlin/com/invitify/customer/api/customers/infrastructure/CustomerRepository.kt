package com.invitify.customer.api.customers.infrastructure

import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerCreateRequest
import com.invitify.database.generated.invitify_database.Tables
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    val dsl: DSLContext
) {
    fun createCustomer(request: CustomerCreateRequest): Customer {
        val customer = Customer(
            name = request.name,
            email = request.email
        )

        return upsertCustomer(customer)
    }

    private fun upsertCustomer(customer: Customer): Customer {
        if (customer.id == null) {
            val result = dsl.insertInto(Tables.CUSTOMERS)
                .set(Tables.CUSTOMERS.NAME, customer.name)
                .set(Tables.CUSTOMERS.EMAIL, customer.email)
                .set(Tables.CUSTOMERS.CREATED_AT, java.time.OffsetDateTime.now().toLocalDateTime())
                .set(Tables.CUSTOMERS.UPDATED_AT, java.time.OffsetDateTime.now().toLocalDateTime())
                .set(Tables.CUSTOMERS.CREATED_TRACE, "invitify-customer-api")
                .set(Tables.CUSTOMERS.UPDATED_TRACE, "invitify-customer-api")
                .returning(Tables.CUSTOMERS.CUSTOMER_ID)
                .fetchOne()

            return Customer(
                id = result?.get(Tables.CUSTOMERS.CUSTOMER_ID),
                name = customer.name,
                email = customer.email
            )
        } else {
            dsl.update(Tables.CUSTOMERS)
                .set(Tables.CUSTOMERS.NAME, customer.name)
                .set(Tables.CUSTOMERS.EMAIL, customer.email)
                .set(Tables.CUSTOMERS.UPDATED_AT, java.time.OffsetDateTime.now().toLocalDateTime())
                .set(Tables.CUSTOMERS.UPDATED_TRACE, "invitify-customer-api")
                .where(Tables.CUSTOMERS.CUSTOMER_ID.eq(customer.id.toInt()))
                .execute()

            return customer
        }
    }
}