package com.ben.firstcoroutine

import com.github.kittinunf.fuel.core.Request
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*

suspend fun <T> retry(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 500, // 0.5 second
    maxDelay: Long = 3000,    // 3 seconds
    factor: Double = 2.0,
    block: suspend () -> T): T
{
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: Exception) {
            println(e)
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val result = retry { makeRequest().await() }
    println("Got result: ${result}")
}
















suspend fun makeRequest() : Deferred<String> {
    return GlobalScope.async {
        Random().nextInt(10).let {
            if (it < 7) {
                throw Exception("Request failure")
            } else {
                "*Succesful Response*"
            }
        }
    }
}