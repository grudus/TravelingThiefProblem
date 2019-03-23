package pwr.jakubgruda.algorithm.selection

import kotlin.random.Random

class RouletteSelection(private val alpha: Double = 1.05, private val beta: Double = 3.0): Selection {

    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        val finesses = population.map(fitness)
        val min = finesses.min()!!

        val weighs = finesses.map { beta * (it + Math.abs(min * alpha))}

        return doSelect(weighs, population)
     }

    private fun <T> doSelect(weighs: List<Double>, population: List<List<T>>): MutableList<List<T>> {
        val max = weighs.max()!!
        val parents = mutableListOf<List<T>>()

        val sortedWeights = weighs.zip(population)
                .sortedByDescending { it.first }

        while (true) {
            val roulette = Random.nextDouble() * max

            for ((weight, genotype) in sortedWeights) {
                if (weight > roulette) {
                    parents += genotype
                    if (parents.size >= population.size / 2 + 1) {
                        return parents
                    }
                }
                else // because they're sorted, all weights after that won't fit this predicate
                    break
            }
        }
    }
}
