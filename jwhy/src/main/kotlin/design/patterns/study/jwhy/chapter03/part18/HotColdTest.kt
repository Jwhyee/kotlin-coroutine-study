package design.patterns.study.jwhy.chapter03.part18

fun main() {
   val l1 = buildList {
      repeat(3) {
         add("User $it")
         println("L: Added User")
      }
   }

   val l2 = l1.map {
      println("L: Processing")
      "Processed $it"
   }

   val s = sequence {
      repeat(3) {
         yield("User $it")
         println("S: Added User")
      }
   }
}