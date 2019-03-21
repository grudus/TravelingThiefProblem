package pwr.jakubgruda.algorithm.selection

import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo

interface Selection {
    fun <T> selectParents(population: List<List<T>>, info: GeneticAlgorithmInfo, fitness: (List<T>) -> Double): List<List<T>>
}
