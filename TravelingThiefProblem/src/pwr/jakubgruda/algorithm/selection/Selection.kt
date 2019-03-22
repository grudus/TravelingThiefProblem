package pwr.jakubgruda.algorithm.selection

interface Selection {
    fun <T> selectParents(population: List<List<T>>, fitness: (List<T>) -> Double): List<List<T>>
}
