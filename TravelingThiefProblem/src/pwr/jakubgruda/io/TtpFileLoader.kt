package pwr.jakubgruda.io

import pwr.jakubgruda.domain.EdgeWeightType
import pwr.jakubgruda.domain.TtpProblemDescription
import java.io.File
import java.nio.file.Files
import kotlin.streams.toList


class TtpFileLoader {


    fun load(file: File): TtpProblemDescription {
        val ttp = Files.lines(file.toPath())
                .limit(10)
                .map { it.split(Regex(":\\s+")).drop(1).joinToString("") }
                .toList()

        return TtpProblemDescription(
                problemName = ttp[0],
                knapsackDataType = ttp[1],
                dimension = ttp[2].toInt(),
                numberOfItems = ttp[3].toInt(),
                capacityOfKnapsack =  ttp[4].toInt(),
                minSpeed =  ttp[5].toDouble(),
                maxSpeed =  ttp[6].toDouble(),
                rentingRatio = ttp[7].toDouble(),
                edgeWeightType = EdgeWeightType.valueOf(ttp[8])
        )
    }

}
