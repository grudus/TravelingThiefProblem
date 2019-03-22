package pwr.jakubgruda.algorithm.selection

import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo

class OnlyBestSelection: Selection {


    override fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>> {
        return population.sortedByDescending(fitness)
                .take(population.size / 2 + 1)
    }
}
