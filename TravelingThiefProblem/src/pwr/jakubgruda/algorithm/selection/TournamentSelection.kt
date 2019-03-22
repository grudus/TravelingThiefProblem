package pwr.jakubgruda.algorithm.selection

class TournamentSelection(private val tournamentSize: Int) : Selection {

    override fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>> {
        val copy = population.toList()

        return generateSequence {
            copy
                    .shuffled()
                    .take(tournamentSize)
                    .maxBy(fitness)
        }
                .take(population.size / 2 + 1)
                .toList()
    }
}
