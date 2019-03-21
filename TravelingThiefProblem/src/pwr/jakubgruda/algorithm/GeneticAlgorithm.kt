package pwr.jakubgruda.algorithm

import pwr.jakubgruda.algorithm.crossover.Crossover
import pwr.jakubgruda.algorithm.mutation.Mutation
import pwr.jakubgruda.algorithm.selection.Selection

object GeneticAlgorithm {

    fun <T> solve(
            info: GeneticAlgorithmInfo,
            initialPopulation: List<List<T>>,
            selection: Selection,
            crossover: Crossover,
            mutation: Mutation,
            fitness: (List<T>) -> Double,
            afterEachPopulation: (Int, List<List<T>>) -> Unit
    ): Double {
        var population = initialPopulation
        var currentGeneration = 1
        var parents: List<List<T>>

        while (currentGeneration <= info.numberOfGenerations) {

            parents = selection.selectParents(population, info, fitness)

            val children = mutableListOf<List<T>>()
            for (i in 0 until parents.size - 1) {
                val parent1 = parents[i]
                val parent2 = parents[i + 1]

                val (child1, child2) = crossover.perform(parent1, parent2, info.crossoverProbability)

                children += mutation.perform(child1, info.mutationProbability)
                children += mutation.perform(child2, info.mutationProbability)
            }

            population = children.toList()

            afterEachPopulation(currentGeneration, population)
            currentGeneration++
        }


        return population.map(fitness).max()!!
    }
}
