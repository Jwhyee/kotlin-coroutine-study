package design.patterns.study.jwhy.chapter03.part21

import kotlinx.coroutines.flow.flowOf

suspend fun main() {
   flowOf(1, 2, 3, 4, 5)
      .collect { println(it) }
}