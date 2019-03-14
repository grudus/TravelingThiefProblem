package pwr.jakubgruda.algorithm

import pwr.jakubgruda.domain.TtpProblemDescription

object GeneticAlgorithm {


    fun <T> solve(
            info: GeneticAlgorithmInfo,
            initialPopulation: List<List<T>>,
            fitness: (List<T>) -> Double
    ): List<T> {
        var currentGeneration = 0

        return emptyList()
    }
}
