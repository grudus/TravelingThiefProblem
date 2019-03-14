package pwr.jakubgruda.algorithm

import pwr.jakubgruda.domain.Path
import pwr.jakubgruda.domain.TtpProblemDescription

object InitialPopulationGenerator {


    fun generate(ttpProblemDescription: TtpProblemDescription, populationSize: Int): List<Path> =
            (0 until populationSize)
                    .map { ttpProblemDescription.cities.shuffled() }

}
