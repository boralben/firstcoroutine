package com.ben.firstcoroutine

import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.*

fun getRequest(url: String) : Job {
    return GlobalScope.launch {
        println("GET: $url")
        Fuel.get(url)
            .response { _, response, _ ->
                println("RESPONSE[$url]: ${response.statusCode}")
            }
    }
}

fun main(args: Array<String>) = runBlocking {
    val httpbin = getRequest("https://httpbin.org/get")
    val dummyRest1 = getRequest("http://dummy.restapiexample.com/api/v1/employees")
    val jsonPlaceHolder = getRequest("https://jsonplaceholder.typicode.com/todos/1")

    httpbin.join()
    dummyRest1.join()
    jsonPlaceHolder.join()

    println("Got all responses");
}

