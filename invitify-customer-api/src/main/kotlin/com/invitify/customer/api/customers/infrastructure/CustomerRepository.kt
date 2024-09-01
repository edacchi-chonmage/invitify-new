package com.invitify.customer.api.customers.infrastructure

import com.invitify.customer.api.customers.model.Customer
import com.invitify.customer.api.customers.model.CustomerEditRequest
import com.invitify.customer.api.customers.model.CustomerRegisterRequest
import com.invitify.database.generated.invitify_database.Tables
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    val dsl: DSLContext
) {
    fun search(): List<Customer> {
        return dsl.select(
            Tables.CUSTOMERS.CUSTOMER_ID,
            Tables.CUSTOMERS.NAME,
            Tables.CUSTOMERS.EMAIL
        )
            .from(Tables.CUSTOMERS)
            .fetch()
            .map {
                Customer(
                    id = it.get(Tables.CUSTOMERS.CUSTOMER_ID),
                    name = it.get(Tables.CUSTOMERS.NAME),
                    email = it.get(Tables.CUSTOMERS.EMAIL)
                )
            }
    }

    fun findById(id: Int): Customer {
        return dsl.select(
            Tables.CUSTOMERS.CUSTOMER_ID,
            Tables.CUSTOMERS.NAME,
            Tables.CUSTOMERS.EMAIL
        )
            .from(Tables.CUSTOMERS)
            .where(Tables.CUSTOMERS.CUSTOMER_ID.eq(id))
            .fetchOne()
            .let {
                Customer(
                    id = it?.get(Tables.CUSTOMERS.CUSTOMER_ID),
                    name = it?.get(Tables.CUSTOMERS.NAME) ?: "",
                    email = it?.get(Tables.CUSTOMERS.EMAIL) ?: ""
                )
            }
    }

    fun register(request: CustomerRegisterRequest): Customer {
        return upsert(
            Customer(
                name = request.name,
                email = request.email
            )
        )
    }

    fun update(id: Int, request: CustomerEditRequest): Customer {
        return upsert(
            Customer(
                id = id,
                name = request.name,
                email = request.email
            )
        )
    }

    private fun upsert(customer: Customer): Customer {
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