package pwr.jakubgruda

import pwr.jakubgruda.algorithm.GeneticAlgorithm
import pwr.jakubgruda.algorithm.GeneticAlgorithmInfo
import pwr.jakubgruda.algorithm.InitialPopulationGenerator
import pwr.jakubgruda.algorithm.chart.ChartCreator
import pwr.jakubgruda.algorithm.crossover.OxCrossover
import pwr.jakubgruda.algorithm.fitness.FitnessCalculator
import pwr.jakubgruda.algorithm.mutation.ReverseSectionMutation
import pwr.jakubgruda.algorithm.selection.OnlyBestSelection
import pwr.jakubgruda.algorithm.selection.ReplicateNBestSelection
import pwr.jakubgruda.algorithm.selection.RouletteSelection
import pwr.jakubgruda.algorithm.selection.TournamentSelection
import pwr.jakubgruda.domain.Path
import pwr.jakubgruda.io.TtpFileLoader
import java.io.File
import java.lang.RuntimeException
import java.util.*

private const val LEVEL = "hard"
private const val FILE_PATH = "TravelingThiefProblem/res/${LEVEL}_1.ttp"
private const val CHART_DEFINITION_PATH = "TravelingThiefProblem/chart-definition.json"

private const val POPULATION_SIZE = 500
private const val TOURNAMENT_SIZE = POPULATION_SIZE / 10
private const val NUMBER_OF_GENERATIONS = 1000
private const val MUTATION_PROBABILITY = 0.25
private const val CROSSOVER_PROBABILITY = 0.7

private const val RESULT_FILE_PATTERN = "TravelingThiefProblem/results/%d___lvl-${LEVEL}_pop-${POPULATION_SIZE}_gen-${NUMBER_OF_GENERATIONS}_mut-${MUTATION_PROBABILITY}_cros-${CROSSOVER_PROBABILITY}.csv"

fun main(args: Array<String>) {
    println("__ Traveling Thief Problem __")
    val description = TtpFileLoader().load(File(FILE_PATH))

    val initialPopulation = InitialPopulationGenerator.generate(description, POPULATION_SIZE)
    val fitnessCalculator = FitnessCalculator(description)


    val resultsFile = File(String.format(RESULT_FILE_PATTERN, Date().time))
    resultsFile.writeText("Generation,Best,Average,Worst")

    GeneticAlgorithm.solve(
            GeneticAlgorithmInfo(MUTATION_PROBABILITY, CROSSOVER_PROBABILITY, NUMBER_OF_GENERATIONS),
            initialPopulation,
            RouletteSelection(),
            OxCrossover(),
            ReverseSectionMutation(),
            fitnessCalculator::calculate
    ) { gen, population -> writeGenerationToFile(gen, population, fitnessCalculator, resultsFile)}


    ChartCreator(CHART_DEFINITION_PATH).create(resultsFile)
}

private fun writeGenerationToFile(generation: Int, population: List<Path>, fitnessCalculator: FitnessCalculator, file: File) {
    val sorted = population.map(fitnessCalculator::calculate).sorted()
    val best = sorted.last()
    val worst = sorted.first()
    val avg = sorted.average()

    print("\r${100 * generation / NUMBER_OF_GENERATIONS}%")

    file.appendText("\n$generation,$best,$avg,$worst")
}
