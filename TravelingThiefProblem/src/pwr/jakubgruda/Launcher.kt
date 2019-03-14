package pwr.jakubgruda

import pwr.jakubgruda.algorithm.FitnessCalculator
import pwr.jakubgruda.algorithm.GeneticAlgorithm
import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo
import pwr.jakubgruda.algorithm.InitialPopulationGenerator
import pwr.jakubgruda.io.TtpFileLoader
import java.io.File

private const val LEVEL = "medium"
private const val FILE_PATH = "TravelingThiefProblem/res/${LEVEL}_0.ttp"


private const val POPULATION_SIZE = 100
private const val TOURNAMENT_SIZE = POPULATION_SIZE / 3
private const val NUMBER_OF_GENERATIONS = 100
private const val MUTATION_PROBABILITY = 0.3
private const val CROSSOVER_PROBABILITY = 0.6

fun main(args: Array<String>) {
    println("__ Traveling Thief Problem __")
    val description = TtpFileLoader().load(File(FILE_PATH))

    val population = InitialPopulationGenerator.generate(description, POPULATION_SIZE)
    val fitnessCalculator = FitnessCalculator(description)

    val bestPath = GeneticAlgorithm.solve(
            GeneticAlgorithmInfo(MUTATION_PROBABILITY, CROSSOVER_PROBABILITY, NUMBER_OF_GENERATIONS, TOURNAMENT_SIZE),
            population,
            fitnessCalculator::calculate
    )

    println(bestPath)

}
