package pwr.jakubgruda

import pwr.jakubgruda.algorithm.FitnessCalculator
import pwr.jakubgruda.algorithm.GeneticAlgorithm
import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo
import pwr.jakubgruda.algorithm.InitialPopulationGenerator
import pwr.jakubgruda.domain.Path
import pwr.jakubgruda.io.TtpFileLoader
import java.io.File
import java.util.*

private const val LEVEL = "hard"
private const val FILE_PATH = "TravelingThiefProblem/res/${LEVEL}_0.ttp"
private const val RESULT_FILE_PATTERN = "TravelingThiefProblem/results/lvl-%s_pop-%d_gen-%d_mut-%.2f_cros-%.2f___%d.csv"


private const val POPULATION_SIZE = 100
private const val TOURNAMENT_SIZE = POPULATION_SIZE / 3
private const val NUMBER_OF_GENERATIONS = 100
private const val MUTATION_PROBABILITY = 0.3
private const val CROSSOVER_PROBABILITY = 0.6

fun main(args: Array<String>) {
    println("__ Traveling Thief Problem __")
    val description = TtpFileLoader().load(File(FILE_PATH))

    val initialPopulation = InitialPopulationGenerator.generate(description, POPULATION_SIZE)
    val fitnessCalculator = FitnessCalculator(description)


    val resultsFile = File(String.format(RESULT_FILE_PATTERN, LEVEL, POPULATION_SIZE, NUMBER_OF_GENERATIONS, MUTATION_PROBABILITY, CROSSOVER_PROBABILITY, Date().time))
    resultsFile.writeText("Generation,Best,Average,Worst")

    GeneticAlgorithm.solve(
            GeneticAlgorithmInfo(MUTATION_PROBABILITY, CROSSOVER_PROBABILITY, NUMBER_OF_GENERATIONS, TOURNAMENT_SIZE),
            initialPopulation,
            fitnessCalculator::calculate
    ) { gen, population -> writeGenerationToFile(gen, population, fitnessCalculator, resultsFile)}
}

private fun writeGenerationToFile(generation: Int, population: List<Path>, fitnessCalculator: FitnessCalculator, file: File) {
    val sorted = population.map(fitnessCalculator::calculate).sorted()
    val best = sorted.last()
    val worst = sorted.first()
    val avg = sorted.average()

    print("\r${100 * generation / NUMBER_OF_GENERATIONS}%")

    file.appendText("\n$generation,$best,$avg,$worst")
}
