package pwr.jakubgruda.algorithm

object GeneticAlgorithm {

    fun <T> solve(
            info: GeneticAlgorithmInfo,
            initialPopulation: List<List<T>>,
            fitness: (List<T>) -> Double
    ): List<T> {
        var population = initialPopulation
        var currentGeneration = 0
        var parents: List<List<T>>

        while (currentGeneration < info.numberOfGenerations) {

            parents = selectParents(population, info, fitness)


            val children = mutableListOf<List<T>>()
            for (i in 0 until parents.size - 1) {
                val parent1 = parents[i]
                val parent2 = parents[i+1]

                val (child1, child2) = Crossover.perform(parent1, parent2, info.crossoverProbability)

                children += Mutation.perform(child1, info.mutationProbability)
                children += Mutation.perform(child2, info.mutationProbability)
            }

            population = children.sortedByDescending { fitness(it) }.take(children.size/2+1)

            val sorted = population.map(fitness).sorted()
            val best = sorted.last()
            val worst = sorted.first()
            val avg = sorted.average()


            println("$currentGeneration,$best,$avg,$worst")

            currentGeneration++
        }


        return population.maxBy { fitness(it) }!!
    }

    private fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>> {
        val parents = mutableListOf<List<T>>()

        while (parents.size < population.size) {
            val tournament = population
                    .shuffled()
                    .take(info.tournamentSize)

            parents += tournament.maxBy { fitness(it) }!!
        }
        return parents.toList()
    }
}
