package com.ben.firstcoroutine

import kotlinx.coroutines.*

fun main() = runBlocking {

    val jobs = mutableListOf<Job>()
    for (i in 1..500_000L) {
        val job = launch {
            delay(1000)
            print('.')
        }
        jobs.add(job)
    }
    println("before delay")
    jobs.joinAll() // suspends coroutine here until all jobs are done
    println("done")
}