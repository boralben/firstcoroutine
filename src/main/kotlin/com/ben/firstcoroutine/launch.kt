package com.ben.firstcoroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // Start a coroutine
    GlobalScope.launch {
        delay(1000)
        println("Hello")
    }

    println("Before Sleep")
    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")
}