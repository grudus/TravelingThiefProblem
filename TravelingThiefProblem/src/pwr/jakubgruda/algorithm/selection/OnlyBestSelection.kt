package pwr.jakubgruda.algorithm.selection

class OnlyBestSelection: Selection {


    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        return population.sortedByDescending(fitness)
                .take(population.size / 2 + 1)
    }
}
