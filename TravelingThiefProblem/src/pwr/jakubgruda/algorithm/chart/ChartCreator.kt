package pwr.jakubgruda.algorithm.chart

import java.io.File

class ChartCreator(private val chartDefinitionPath: String) {

    fun create(data: File) {
        Runtime.getRuntime()
                .exec("c3-chart-maker ${data.absolutePath} --chart=$chartDefinitionPath --out=${data.absolutePath.replace("csv", "png")}")
    }
}
