package pwr.jakubgruda.algorithm.mutation

import kotlin.random.Random

class ReverseSectionMutation: Mutation {

    override fun <T> perform(chromosome: List<T>, probability: Double): List<T> {
        val shouldPerform = Random.nextDouble(0.0, 1.0) <= probability

        return if (shouldPerform)
            doMutation(chromosome)
        else chromosome
    }

    private fun <T> doMutation(chromosome: List<T>): List<T> {
        val mutationIndex1 = Random.nextInt(chromosome.size)
        val mutationIndex2 = Random.nextInt(chromosome.size)

        val min = Math.min(mutationIndex1, mutationIndex2)
        val max = Math.max(mutationIndex1, mutationIndex2)

        return chromosome.take(min) + chromosome.subList(min, max).reversed() + chromosome.subList(max, chromosome.size)
    }
}
