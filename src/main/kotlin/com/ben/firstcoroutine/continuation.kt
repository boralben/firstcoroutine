package com.ben.firstcoroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        doLongTasks()
    }
    println("It took $time milliseconds")
}

suspend fun doLongTasks() : Unit {
    val first = getFirstThing()
    val second = getSecondThingBasedOnFirstThing(first)
    println("Did Long Tasks")
}

suspend fun getFirstThing(): Long {
    delay(1000)
    println("Got First Thing")
    return 1000
}

suspend fun getSecondThingBasedOnFirstThing(firstThing: Long) : Long {
    delay(2 * firstThing)
    println("Got Second Thing")
    return 2 * 1000
}



























/*

pseudocode of compiled suspend fun
fun doLongTasks(continuation: Continuation) : Any {
    val sm = continuation as? StateMachine object : StateMachine { // defines current execution state
        val label // current step we're on
        fun resume() {
            doLongTasks(this)
        }
     }
    switch(sm.label) {
        case 0:
            sm.label = 1 // next operation
            doLongTask(1000, sm) // when doLongTask completes, resume function of execution context gets called
        case 1:
            sm.res1 = sm.result
            sm.label = 2
            doLongTask(2000, sm)
        case 2:
            val res2 = sm.result
            println("Did Long Tasks")
    }
}

*/
/*
1. Note suspension points in IDE
2. suspend keywords adds a continuation (callback) param to fun once compiled
3. continuation is a callback for the remainder of the work to be done
4. Each suspension point corresponds to switch cases in compiled pseudocode
5. As we work through the suspend fun, we keep track of execution state.
   On resume, execution state tells us the next task to complete
 */

// Source: https://www.youtube.com/watch?v=YrrUCSi72E8