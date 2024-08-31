package com.invitify.customer.api.sample

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController(private val dsl: DSLContext) {
    @GetMapping("")
    fun getSample(): String {
        // fetchした結果をJSON文字列で返す
        val res = DSL.using(dsl.configuration())
            .select(
                DSL.field("id"),
            )
            .from("sample")
            .fetch()
            .formatJSON()

        return res
    }
}