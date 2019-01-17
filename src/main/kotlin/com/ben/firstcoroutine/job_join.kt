package com.ben.firstcoroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Start")

    // Start a coroutine
    val job = launch {
        delay(1000)
        println("Hello")
    }
    println("Stop1")
    job.join() // suspends coroutine here until job is done
    println("Stop2")
}