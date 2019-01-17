import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferreds = mutableListOf<Deferred<Int>>()
    for (i in 1..5) {
        val deferred = async {
            delay(1000)
            println("hi")
            i
        }
        println("Creating ${deferred.toString()}")
        deferreds.add(deferred)

    }
//    deferreds.awaitAll()
    for (deferred in deferreds) {
        println("Deferred val: ${deferred.await()}")
    }
    println("Done")
}
