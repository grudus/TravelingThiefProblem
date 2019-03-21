package pwr.jakubgruda.algorithm.crossover

interface Crossover {
    fun <T> perform(parent1: List<T>, parent2: List<T>, probability: Double): Pair<List<T>, List<T>>
}
