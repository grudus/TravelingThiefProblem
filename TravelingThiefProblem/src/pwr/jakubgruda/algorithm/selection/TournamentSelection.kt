package pwr.jakubgruda.algorithm.selection

import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo

class TournamentSelection: Selection {


    override fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>> {
        val parents = mutableListOf<List<T>>()

        while (parents.size <= population.size / 2) {
            val tournament = population
                    .shuffled()
                    .take(info.tournamentSize)

            parents += tournament.maxBy { fitness(it) }!!
        }
        return parents.toList()
    }
}
