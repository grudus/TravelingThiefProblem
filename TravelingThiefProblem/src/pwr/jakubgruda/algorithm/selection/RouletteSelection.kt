package pwr.jakubgruda.algorithm.selection

import kotlin.random.Random

class RouletteSelection: Selection {

    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        val finesses = population.map(fitness)
        val minWeight = finesses.min()!!

        val weighs = finesses.map { it + Math.abs(minWeight) }
                .map { 7 * it * it }
        val sum = weighs.sum()

        return generateSequence { population[findIndex(weighs, sum)] }
                .take(population.size / 2 + 1)
                .toList()
     }

    private fun findIndex(weighs: List<Double>, sum: Double): Int {
        val guess = Random.nextDouble() * sum

        var rouletteValue = guess

        for (i in weighs.indices) {
            rouletteValue -= weighs[i]
            if (rouletteValue < 0)
                return i
        }
        return weighs.lastIndex
    }
}
