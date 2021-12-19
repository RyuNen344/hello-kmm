package com.ryunen344.hello.kmm

class Greeting {
    fun greeting() : String {
        return "Hello, ${Platform().platform}!"
    }
}
