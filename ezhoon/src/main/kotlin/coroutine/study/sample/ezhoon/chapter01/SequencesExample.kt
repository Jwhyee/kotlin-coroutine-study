package coroutine.study.sample.ezhoon.chapter01


// https://kotlinlang.org/docs/sequences.html
fun main() {
    // argument를 나열하는 sequenceOf로 함수로 sequence를 만들 수 있다
    val numberSequence = sequenceOf("four", "three", "two", "one")

    // List와 Set과 같은 Iterable을 갖고 있다면 asSequence 함수를 통해 sequence를 생성할 수 있다.
    val numbers = listOf("one", "two", "three")
    val numberSequence2 = numbers.asSequence()

    // element를 생성하는 함수를 argument로 사용해서 sequence를 생성하는 방법으로 generateSequence()를 사용해서 가능하다.
    // element의 seed 값을 지정하거나 함수 호출의 결과를 지정하는 것도 가능하다.
    val oddNumbers = generateSequence(1) { it + 2 } // seed(시작값)은1로 그 다음 값들은 이전 값의 + 2
    println(oddNumbers.take(10).toList()) // [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
    // println(oddNumbers.count()) // generateSequence은 take(N)과 같이 수를 제한하지 않으면 무한해지므로 에러가 난다.

    // null을 리턴하면 유한한 sequence를 만들 수 있다.
    val oddNumbersLimited = generateSequence(1) { if (it < 10) it + 2 else null }
    println(oddNumbersLimited.take(20).toList()) // [1, 3, 5, 7, 9, 11]

    // 임의의 크기의 chunk의 각각의 element로 sequence를 생성할 수 있다.
    val oddNumbersYield = sequence {
        yield(10) // yield는 한 개의 element를 argument로 갖는다.
        yieldAll(listOf(3, 5)) // Iterable 또는 Iterator 또 다른 Sequence를 갖는다.
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbersYield.take(5).toList())

    // Iterable 예시1
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthList = words
        .filter {
            println("filter: $it")
            it.length > 3
        }.map {
            println("length: ${it.length}, $it")
            it.length
        }
        .take(4)
    // filter에서 모든 element에 대해 동작이 수행되고 map에서 filter로 리턴된 element에 대해 수행한다.
    println("Length of first 4 words longer than 3 chars: $lengthList")

    // Sequence 예시
    val wordSequence = words.asSequence()
    val wordSequenceList = wordSequence
        .filter {
            println("filter: $it")
            it.length > 3
        }.map {
            println("length: ${it.length}, $it")
            it.length
        }.take(4).toList()
    /*
        filter: The
        filter: quick
        length: 5, quick
        filter: brown
        length: 5, brown
        filter: fox
        filter: jumps
        length: 5, jumps
        filter: over
        length: 4, over
     */
    // 결과를 보면 filter후에 map이 실제 필요로 하는 때에 호출된다.
    // map이 filter가 실행이 되는 즉 length가 3초과일 때 map이 바로 실행이 된다.
    // take()에 의해 최종 결과가 4개를 만족하자 나머지 수행은 멈추고 모든 동작이 마무리 된다. -> the lazy dog가 실행 안되는 모습
    println("Length of first 4 words longer than 3 chars: $wordSequenceList")
}