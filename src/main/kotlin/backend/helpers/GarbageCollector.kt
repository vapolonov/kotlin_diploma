package backend.helpers

object GarbageCollector {
  val users: MutableList<Int> = mutableListOf()
  val products: MutableList<Int> = mutableListOf()
  val orders: MutableList<Int> = mutableListOf()
}