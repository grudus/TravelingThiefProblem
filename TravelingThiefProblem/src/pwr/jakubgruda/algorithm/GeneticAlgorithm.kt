package pwr.jakubgruda.algorithm

object GeneticAlgorithm {

    fun <T> solve(
            info: GeneticAlgorithmInfo,
            initialPopulation: List<List<T>>,
            fitness: (List<T>) -> Double,
            afterEachPopulation: (Int, List<List<T>>) -> Unit
    ): Double {
        var population = initialPopulation
        var currentGeneration = 1
        var parents: List<List<T>>

        while (currentGeneration <= info.numberOfGenerations) {

            parents = selectParents(population, info, fitness)


            val children = mutableListOf<List<T>>()
            for (i in 0 until parents.size - 1) {
                val parent1 = parents[i]
                val parent2 = parents[i + 1]

                val (child1, child2) = Crossover.perform(parent1, parent2, info.crossoverProbability)

                children += Mutation.perform(child1, info.mutationProbability)
                children += Mutation.perform(child2, info.mutationProbability)
            }

            population = children.toList()

            afterEachPopulation(currentGeneration, population)
            currentGeneration++
        }


        return population.map(fitness).max()!!
    }

    private fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>> {
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
