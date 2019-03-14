package pwr.jakubgruda.io

import pwr.jakubgruda.domain.City
import pwr.jakubgruda.domain.EdgeWeightType
import pwr.jakubgruda.domain.Item
import pwr.jakubgruda.domain.TtpProblemDescription
import java.io.File
import java.nio.file.Files
import kotlin.streams.toList


class TtpFileLoader {


    fun load(file: File): TtpProblemDescription {
        val lines = Files.lines(file.toPath())
                .toList()

        val ttp = lines
                .take(10)
                .map { it.split(Regex(":\\s+")).drop(1).joinToString("") }
                .toList()

        val cities = lines
                .drop(11)
                .takeWhile { !it.startsWith("ITEMS SECTION") }
                .map { it.split(Regex("\\s+")).map { it.toDouble() } }
                .map { City(it[0].toInt(), it[1], it[2]) }

        val items = lines
                .dropWhile { !it.startsWith("ITEMS SECTION") }
                .drop(1)
                .map { it.split(Regex("\\s+")).map { it.toInt() } }
                .map { Item(it[0], it[1], it[2], it[3]) }

        return TtpProblemDescription(
                problemName = ttp[0],
                knapsackDataType = ttp[1],
                dimension = ttp[2].toInt(),
                numberOfItems = ttp[3].toInt(),
                capacityOfKnapsack =  ttp[4].toInt(),
                minSpeed =  ttp[5].toDouble(),
                maxSpeed =  ttp[6].toDouble(),
                rentingRatio = ttp[7].toDouble(),
                edgeWeightType = EdgeWeightType.valueOf(ttp[8]),
                cities = cities,
                items = items
        )
    }

}
