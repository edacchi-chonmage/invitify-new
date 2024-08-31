package com.invitify.customer.api.sample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController {
    @GetMapping("")
    fun getSample(): String {
        return "Hello, World!"
    }
}