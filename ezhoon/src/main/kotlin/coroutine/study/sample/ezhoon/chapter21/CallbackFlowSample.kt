package coroutine.study.sample.ezhoon.chapter21

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

fun interface LocationCallback {
    fun onLocationChanged(location: String)
}

class LocationManager {
    private var callback: LocationCallback? = null

    fun startLocationUpdates(callback: LocationCallback) {
        this.callback = callback
        GlobalScope.launch {
            repeat(5) {
                delay(1000L)
                println("callback $it")
                callback.onLocationChanged("Location $it")
            }
        }
    }

    fun stopLocationUpdates() {
        callback = null
    }
}

fun locationFlow(locationManager: LocationManager): Flow<String> = callbackFlow {
    val callback = LocationCallback { location ->
        trySend(location)
    }
    locationManager.startLocationUpdates(callback)

    awaitClose {
        println("await close")
        locationManager.stopLocationUpdates()
    }
}

fun main() = runBlocking {
    val locationManager = LocationManager()
    val job = launch {
        locationFlow(locationManager)
            .flowOn(Dispatchers.IO)
            .collect { location ->
                println("Collected: $location")
            }
    }

    delay(7000L)
    println("cancelAndJoin()")
    job.cancelAndJoin()
}