
package com.ben.firstcoroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferred = async {
        println("1")
        delay(3000)
        println("2")
        "Hi everybody"
    }
    println("Deferred val: ${deferred.await()}")
    println("Done")
}
