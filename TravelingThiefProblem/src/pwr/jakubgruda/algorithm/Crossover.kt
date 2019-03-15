package pwr.jakubgruda.algorithm

import java.util.*
import kotlin.random.Random

object Crossover {


    fun <T> perform(parent1: List<T>, parent2: List<T>, probability: Double): Pair<List<T>, List<T>> {
        val shouldPerform = Random.nextDouble(0.0, 1.0) <= probability

        return if (shouldPerform)
            doCrossover(parent1, parent2)
        else Pair(parent1, parent2)
    }

    // OX based on https://stackoverflow.com/a/11784059/8795000
    private fun <T> doCrossover(parent1: List<T>, parent2: List<T>): Pair<List<T>, List<T>> {
        val size = parent1.size

        val index1 = Random.nextInt(size)
        val index2 = Random.nextInt(size)

        val startIndex = Math.min(index1, index2)
        val endIndex = Math.max(index1, index2)

        val child1 = mutableListOf<T>()
        val child2 = mutableListOf<T>()

        child1.addAll(parent1.subList(startIndex, endIndex))
        child2.addAll(parent2.subList(startIndex, endIndex))

        var currentIndex: Int
        var currentItemInParent1: T
        var currentItemInParent2: T

        for (i in 0 until size) {
            currentIndex = (endIndex + i) % size

            currentItemInParent1 = parent1[currentIndex]
            currentItemInParent2 = parent2[currentIndex]

            if (!child1.contains(currentItemInParent2)) {
                child1 += currentItemInParent2
            }

            if (!child2.contains(currentItemInParent1)) {
                child2 += currentItemInParent1
            }
        }

        Collections.rotate(child1, startIndex)
        Collections.rotate(child2, startIndex)

        return Pair(child1, child2)
    }
}


//abcde   -> ade
//dcaeb   -> daebc
