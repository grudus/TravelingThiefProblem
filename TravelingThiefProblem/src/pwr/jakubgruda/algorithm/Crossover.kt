package pwr.jakubgruda.algorithm

import kotlin.random.Random

object Crossover {


    fun <T> perform(parent1: List<T>, parent2: List<T>, probability: Double): Pair<List<T>, List<T>> {
        val shouldPerform = Random.nextDouble(0.0, 1.0) <= probability

        return if (shouldPerform)
            doCrossover(parent1, parent2)
        else Pair(parent1, parent2)
    }

    private fun <T> doCrossover(parent1: List<T>, parent2: List<T>): Pair<List<T>, List<T>> {
        val newParent1 = parent1.toMutableList()
        val newParent2 = parent2.toMutableList()
        val itemsToTake = Random.nextInt(parent1.size)

        val parent1Items = parent1.shuffled().take(itemsToTake)

        newParent2.removeAll(parent1Items)
        val otherParent2Elements = newParent2.toList()
        newParent2 += parent1Items.shuffled()


        return Pair(parent1Items + otherParent2Elements.shuffled(), newParent2)
    }
}


//abcde   -> ade
//dcaeb   -> daebc
