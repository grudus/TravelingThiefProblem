package pwr.jakubgruda.algorithm.mutation

interface Mutation {
    fun <T> perform(chromosome: List<T>, probability: Double): List<T>
}
