package pwr.jakubgruda.algorithm.mutation

import kotlin.random.Random

class SinglePointMutation: Mutation {

    override fun <T> perform(chromosome: List<T>, probability: Double): List<T> {
        val shouldPerform = Random.nextDouble(0.0, 1.0) <= probability

        return if (shouldPerform)
            doMutation(chromosome)
        else chromosome
    }

    private fun <T> doMutation(chromosome: List<T>): List<T> {
        val mutationIndex1 = Random.nextInt(chromosome.size)
        val mutationIndex2 = Random.nextInt(chromosome.size)

        val mutatedChromosome = chromosome.toMutableList()

        val temp = mutatedChromosome[mutationIndex1]
        mutatedChromosome[mutationIndex1] = mutatedChromosome[mutationIndex2]
        mutatedChromosome[mutationIndex2] = temp

        return mutatedChromosome.toList()
    }
}
