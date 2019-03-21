package pwr.jakubgruda.algorithm

import pwr.jakubgruda.domain.Item
import pwr.jakubgruda.domain.Path
import pwr.jakubgruda.domain.TtpProblemDescription

class FitnessCalculator(private val description: TtpProblemDescription) {
    private val itemsInCities: Map<Int, List<Item>> = description.items
            .groupBy { it.assignedNodeNumber }

    private val deltaV = description.maxSpeed - description.minSpeed

    fun calculate(path: Path): Double {
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
            val velocity = description.maxSpeed - (newKnapsackWeight / description.capacityOfKnapsack) * deltaV

            totalTime += distance / velocity
        }

        return knapsack.sumBy { it.profit } - totalTime
    }

}
