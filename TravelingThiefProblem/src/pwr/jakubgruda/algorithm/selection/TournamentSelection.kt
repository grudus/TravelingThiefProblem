package pwr.jakubgruda.algorithm.selection

import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo

class TournamentSelection : Selection {


    override fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>> {
        val copy = population.toList()

        return generateSequence {
            copy
                    .shuffled()
                    .take(info.tournamentSize)
                    .maxBy(fitness)
        }
                .take(population.size / 2 + 1)
                .toList()
    }
}
