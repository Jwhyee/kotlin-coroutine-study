package coroutine.study.sample.ezhoon.chapter01

import kotlin.coroutines.*

fun main() = doSomething(MyContinuationImpl())

class MyContinuationImpl(
    override val context: CoroutineContext = EmptyCoroutineContext,
    var label: Int = 0,
) : Continuation<String> {

    var result: Any? = null

    override fun resumeWith(result: Result<String>) {
        this.result = result.getOrThrow()
        println("Continuation.resumeWith() 호출 / result > $result\n")
        doSomething(this)
    }
}
fun doSomething(cont: MyContinuationImpl) {
    println("doSomething")
    when(cont.label) {
        0 -> {
            cont.label = 1
            signup(cont)
        }
        1 -> {
            val userData = cont.result
            cont.label = 2
            updateSharedPreference(userData as String, cont)
        }
        2 -> {
            moveFragmentMenuList()
        }
        else -> {
            throw IllegalStateException("")
        }
    }
}

fun signup(cont: MyContinuationImpl) {
    println("signup() 호출")
    println("현재 label -> ${cont.label} / result > ${cont.result}")
    val apiResult = "네트워크 통신 -> Ezhoon 데이터"
    cont.resumeWith(Result.success(apiResult))
}

fun updateSharedPreference(user: String, cont: MyContinuationImpl) {
    println("updateSharedPreference(), 호출")
    println("현재 label -> ${cont.label} / result > ${cont.result}")
    cont.resumeWith(Result.success(user))
}

fun moveFragmentMenuList() {
    println("moveFragmentMenuList(), 호출")
}

