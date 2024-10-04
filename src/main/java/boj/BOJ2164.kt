package boj

import java.util.LinkedList
import java.util.Queue

fun main() {
    val num = readlnOrNull() ?: return
    val queue: Queue<String> = LinkedList()

    (1..num.toInt()).forEach { i ->
        queue.add(i.toString())
    }

    while (queue.size > 1) {
        queue.remove()
        queue.add(queue.remove())
    }

    println(queue.remove())
}
