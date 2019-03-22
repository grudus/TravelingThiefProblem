package pwr.jakubgruda.algorithm.selection

class ReplicateNBestSelection(private val numberOfBest: Int): Selection {


    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        val nBest = population.sortedByDescending(fitness)
                .take(numberOfBest)

        val iterations = (population.size / 2 + 1) / numberOfBest

        return (0 until iterations)
                .flatMap { nBest }
                .take(population.size / 2 + 1)
                .shuffled()
    }
}
