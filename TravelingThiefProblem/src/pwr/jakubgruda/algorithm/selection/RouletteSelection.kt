package pwr.jakubgruda.algorithm.selection

import kotlin.random.Random

class RouletteSelection: Selection {

    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        val finesses = population.map(fitness)
        val min = finesses.min()!!

        val weighs = finesses.map { 3 * (it + Math.abs(min * 1.05))}

        return doSelect(weighs, population)
     }

    private fun <T> doSelect(weighs: List<Double>, population: List<List<T>>): MutableList<List<T>> {
        val sum = weighs.sum()
        val parents = mutableListOf<List<T>>()

        val sortedWeights = weighs.zip(population)
                .sortedByDescending { it.first }

        while (true) {
            val roulette = Random.nextDouble() * sum

            for ((weight, genotype) in sortedWeights) {
                if (weight > roulette) {
                    parents += genotype
                    if (parents.size >= population.size / 2 + 1) {
                        return parents
                    }
                }
                else // because there are sorted, all weights after that won't fit this predicate
                    break
            }
        }
    }
}
