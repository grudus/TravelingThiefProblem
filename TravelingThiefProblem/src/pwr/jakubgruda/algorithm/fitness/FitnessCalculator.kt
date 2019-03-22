package pwr.jakubgruda.algorithm.fitness

import pwr.jakubgruda.domain.Item
import pwr.jakubgruda.domain.Path
import pwr.jakubgruda.domain.TtpProblemDescription

class FitnessCalculator(private val description: TtpProblemDescription) {
    private val itemsInCities: Map<Int, List<Item>> = description.items
            .groupBy { it.assignedNodeNumber }
    private val cache = mutableMapOf<Path, Double>()

    private val deltaV = description.maxSpeed - description.minSpeed

    fun calculate(path: Path): Double {
        if (cache.containsKey(path)) {
            return cache[path]!!
        }

        val knapsack = mutableListOf<Item>()
        var totalTime = 0.0

        for (i in 0 until path.size - 1) {
            val cityA = path[i]
            val cityB = path[i + 1]

            val knapsackWeight = knapsack.sumBy { it.weight }
            val itemToTake = itemsInCities[cityA.index]
                    ?.filter { knapsackWeight + it.weight < description.capacityOfKnapsack }
                    ?.maxBy { it.profit }

            if (itemToTake != null) {
                knapsack += itemToTake
            }
            val newKnapsackWeight = knapsackWeight + (itemToTake?.weight ?: 0)

            val distance = cityA.distanceTo(cityB)
            val velocity = calculateVelocity(newKnapsackWeight)

            totalTime += distance / velocity
        }

        val totalKnapsackWeight = knapsack.sumBy { it.weight }
        val returnToFirstCityVelocity = calculateVelocity(totalKnapsackWeight)
        val returnToFirstCityDistance = path.last().distanceTo(path.first())

        totalTime += returnToFirstCityDistance / returnToFirstCityVelocity

        val fitness = knapsack.sumBy { it.profit } - totalTime
        cache[path] = fitness
        return fitness
    }

    private fun calculateVelocity(knapsackWeight: Int): Double =
            description.maxSpeed - (knapsackWeight / description.capacityOfKnapsack) * deltaV

}
